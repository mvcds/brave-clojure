(def glitter-filter-result [{:name "Edward Cullen", :glitter-index 10}
 {:name "Jacob Black", :glitter-index 3}
 {:name "Carlisle Cullen", :glitter-index 6}])

(defn append
  [item coll]
  (conj coll item))

(append { :name "Nosteratu", :glitter-index -10 } glitter-filter-result)