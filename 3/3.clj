(defn mapset
  [f c]
  (set (map f c)))
(mapset inc [1 1 2 2])