(def glitter-filter-result [{:name "Edward Cullen", :glitter-index 10}
 {:name "Jacob Black", :glitter-index 3}
 {:name "Carlisle Cullen", :glitter-index 6}])

(defn to-list-of-names
  [item]
  (:name item))

(map to-list-of-names glitter-filter-result)