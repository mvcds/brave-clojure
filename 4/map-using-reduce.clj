(defn mur
  [f c]
  (seq (reduce (fn [new-seq value]
            (into new-seq [(f value)]))
          []
          c)))
(= (map inc '(1 2 3)) (mur inc '(1 2 3)))