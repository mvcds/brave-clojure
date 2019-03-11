(defn sur
  [f c]
  (boolean (reduce (fn [new-seq value]
            (or value new-seq))
          nil
          c)))

(= true (sur even? '(1 2)))
(= false (sur even? '(1 3)))