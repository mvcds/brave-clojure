(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "1_eye" :size 1}
                             {:name "1_ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "1_shoulder" :size 3}
                             {:name "1_upper_arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "1_forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "1_kidney" :size 1}
                             {:name "1_hand" :size 2}
                             {:name "1_knee" :size 2}
                             {:name "1_thigh" :size 4}
                             {:name "1_lower_leg" :size 3}
                             {:name "1_achilles" :size 1}
                             {:name "1_foot" :size 2}])

(defn matching-part
  [part n]
  {:name (clojure.string/replace (:name part) #"^1_" (str n "_"))
   :size (:size part)})

(defn create-set-of-parts
  [part number]
  (for [x (range 2 (+ number 1))]
    (matching-part part x)))

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts number]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (create-set-of-parts part number)])))))))

(symmetrize-body-parts asym-hobbit-body-parts 4)