(refer 'clojure.test)

(def precedence
  ['* '/ '+ '-])

(defmacro infix-to-list
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))
 
(defn does-root-have-bigger-precedent?
  [rootNode leafNode]
    (let [a (.indexOf precedence rootNode)
          b (.indexOf precedence leafNode)]
       (< a b)))

(defn is-root-bigger?
  [rootNode leafNode]
    (cond
       (and (function? rootNode) (function? leafNode)) (does-root-have-bigger-precedent? rootNode leafNode)
       :else (function? leafNode)))

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