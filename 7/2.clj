(refer 'clojure.test)

(def precedence
  ['* '/ '+ '-])
 
(defn does-oldNode-have-bigger-precedent?
  [oldNode newNode]
    (let [a (.indexOf precedence oldNode)
          b (.indexOf precedence newNode)]
       (<= a b)))

(defn is-oldNode-bigger?
  [oldNode newNode]
    (cond
       (and (function? oldNode) (function? newNode)) (does-oldNode-have-bigger-precedent? oldNode newNode)
       :else (function? newNode)))

(defn add-node
  ([normalized next-node nodes]
    (let [a (:a normalized)
          operator (:operator normalized)
          b (:b normalized)]
      (if (is-oldNode-bigger? operator next-node)
        { :a (normalize (into [a operator b] (rest nodes))) :operator next-node :b (first nodes) }
        { :a a :operator operator :b (normalize (into [b next-node] nodes)) }))))

(defn read-next-node
  ([normalized nodes]
    (if (empty? nodes)
      (identity normalized)
      (add-node normalized (first nodes) (rest nodes)))))

(defn normalize
  [infixed]
    (let [normal { :a (first infixed) :operator (second infixed) :b (nth infixed 2) }]
      (read-next-node normal (drop 3 infixed))))

(defn evaluate
  [normalized]
    (let [a (:a normalized)
          operator (:operator normalized)
          b (:b normalized)]
      (let [value-a (if (map? a) (evaluate a) (identity a))
            value-b (if (map? b) (evaluate b) (identity b))]
        (list operator value-a value-b))))

(defmacro infix
  [infixed]
  (let [normalized (normalize infixed)]
    (evaluate normalized)))

; (macroexpand '(infix (1 + 3 * 4 - 5)))

(def control (- (+ 1 (* 3 4)) 5))
(def result (infix (1 + 3 * 4 - 5)))

; (println result)

(= control result)