(defn dec-maker [x] (fn [y] (- y x)))
(def dec9 (dec-maker 9))
(= 1 (dec9 10))