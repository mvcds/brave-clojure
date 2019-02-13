(refer 'clojure.test)

(def precedence
  ['* '/ '+ '-])

(defmacro infix-to-list
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))
 
(defn does-oldNode-have-bigger-precedent?
  [oldNode newNode]
    (let [a (.indexOf precedence oldNode)
          b (.indexOf precedence newNode)]
       (< a b)))

(defn is-oldNode-bigger?
  [oldNode newNode]
    (cond
       (and (function? oldNode) (function? newNode)) (does-oldNode-have-bigger-precedent? oldNode newNode)
       :else (function? newNode)))

(defn replace-b
  [a operator b next-node nodes]
    { :a a :operator operator :b (normalize (into [b next-node] nodes)) })

(defn add-node
  ([normalized next-node nodes]
    (let [a (:a normalized) 
          operator (:operator normalized)
          b (:b normalized)]
      (if (is-oldNode-bigger? operator next-node)
        next-node
        (replace-b a operator b next-node nodes)))))

; { :a 1 :operator '+ :b 3 }
; { :a 1 :operator '+ :b { a: 3 :operator '* b: 4 }}

(defn read-next-node
  ([normalized nodes]
    (if (empty? nodes)
      (identity normalized)
      (add-node normalized (first nodes) (rest nodes)))))

(defn normalize
  [infixed]
    (let [normal { :a (first infixed) :operator (second infixed) :b (nth infixed 2) }]
      (read-next-node normal (drop 3 infixed))))

(defmacro infix
  [infixed]
  (list (second infixed)
        (first infixed)
        (nth infixed 2)))

(macroexpand '(infix (1 + 3 * 4 - 5)))

;            1
;                   +
;               3                *
;                 4
;                       5            -

 ; [- 5 * 4 3 + 1]
 ; [- 5 (* 4 3) + 1]
 ; [- 5 (+ (* 4 3) 1)]
 ; [(- 5 (+ (* 4 3) 1)]

; (def control (- (+ 1 (* 3 4)) 5))
; (def result (infix (1 + 3 * 4 - 5)))

; (println result)

; (= control result)