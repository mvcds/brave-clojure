(def first (ref { :life 15 :totalLife 40 :inventory {} }))
(def second (ref { :inventory { :healingPotion 30 } }))

(defn give-item
  ([giver taker]
   (dosync
     (alter giver update-in [:inventory] dissoc :healingPotion)
     (alter taker update-in [:inventory :healingPotion] (identity 30)))))

(give-item second first)

(:inventory @first)
(:inventory @second)