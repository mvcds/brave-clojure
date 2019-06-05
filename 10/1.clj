(def values (atom 0))

(swap! values inc)
(swap! values inc)
(swap! values inc)
(swap! values inc)

@values