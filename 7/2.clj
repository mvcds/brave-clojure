(refer 'clojure.test)

(def precedence
  ['* '/ '+ '-])

(defmacro infix-to-list
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))
 
(defn does-oldOperator-have-bigger-precedent?
  [oldOperator newOperator]
    (let [a (.indexOf precedence oldOperator)
          b (.indexOf precedence newOperator)]
       (< a b)))

(defn is-OldOperator-bigger?
  [oldOperator newOperator]
    (cond
       (and (function? oldOperator) (function? newOperator)) (does-oldOperator-have-bigger-precedent? oldOperator newOperator)
       :else (function? newOperator)))

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