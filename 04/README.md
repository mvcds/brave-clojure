> Programming to Abstractions

An **abstraction** is like a named collection of operations. That's why `map` can handle vectors, sets, lists and more.

> Sequence

May refer to a collection of elements organized in linear order.

If the core sequence functions `first`, `rest`, and `cons` work on a data structure, you can say the data structure implements the sequence abstraction

* `first` returns the value for the requested node
* `rest` returns the remaining values after the requested node
* `cons` adds a new node with the given value to the beginning of the list

If those are implemented, you can use `map`, `reduce`, `filter`, and other seq functions on top of them.

> Indirection

A generic term for the mechanisms a language employs so that one name can have multiple, related meanings. For instance `first` has multiple, data structure–specific meanings. Indirection is what makes abstraction possible.

Clojure also allows for **Polymorphism**. Polymorphic functions dispatch to different function bodies based on the type of the argument supplied.

> `seq`

It produces lists

> `take`

Returns the first n elements of the sequence

```
(take 3 [1 2 3 4 5 6 7 8 9 10])
; => (1 2 3)
```

> `drop`

Removes the first n elements of a sequence

```
(drop 3 [1 2 3 4 5 6 7 8 9 10])
; => (4 5 6 7 8 9 10)
```

> `take-while` and `drop-while`

Do a similar thing but take a predicate to find the point where to stop (first false and first true, repectivelly)

`(drop-while #(< (:month %) 3) food-journal)`

Both functions don't consume all the data, if it is not necessary

> Lazy sequences

A seq which is only created when necessary. Trying to access such data is called *realizing the seq*.

This is done by efficiency but has the advantage of allow to use infinite sequences.

Clojure chunks the values for lazy seqs i.e. it realizes 32(?) items of a sequence at oonce

`(repeat literal)` and `(repeatedly function)` are functions which create a infinite sequence, so they can be used with take`.

> Collection

While a seq implements indivual operations, collections act upon the whole data structure.

* `empty?`
* `every?`
* `count`

> Functions

* `apply`: explodes a seq data structure so that it can be used as a rest parameter => `(= (max 0 1 2) (apply max [0 1 2])`
* `partial`: creates a partial function => (def add10 (partial + 10))
* `complement`: negates a function => `(def isEven? (complement odd?))`
