(defn fur
  [f c]
  (seq (reduce (fn [new-seq value]
            (if (f value)
              (into new-seq [value])
              (identity new-seq)))
          []
          c)))
(= (filter even? '(1 2 3 4)) (fur even? '(1 2 3 4)))