(refer 'clojure.test)

(def precedence
  ['* '/ '+ '-])

(defmacro infix-to-list
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))
 
(defmacro infix
  [infixed]
  (list (second infixed)
        (first infixed)
        (nth infixed 2)))

(macroexpand '(infix (1 + 3 * 4 -5)))

; (def control (+ (* 3 4) 1 -5))
; (def result (infix (1 + 3 * 4 -5)))

; (* 3 4) => 1 + (3 * 4) -5 => 
; (+ 1 12 -5) => (+ 1 12 -5)

; (println result)

; (= control result)