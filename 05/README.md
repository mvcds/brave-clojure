> Referential Transparency

Be able to change the function call by its return value without affecting the program because the function would return that exaclty value

If a function is affected by external conditions, it cannot be considered referentially transparent because it is not predictable e.g. reading from a stream may make the function change its return value over time

> Side effect

A side effect happens when change within a function is observable outside of it

> Pure functions

1. Return the same result if given the same arguments (are *referential transparent*)
2. Cause no side effects

A pure function relies only on its own arguments and immutable values to produce its result

> [Tail call optimization](https://en.wikipedia.org/wiki/Tail_call)

> [Structural sharing](https://hypirion.com/musings/understanding-persistent-vector-pt-1)

> Function composition

Using the return value of a function as an argument to another

Recursion is basically function composition over the same function

* `comp`: creates a new function from the composition of any number of functions, the first function can take any number of arguments but the remaining functions should be unary `(= 7 ((comp inc *) 2 3))`
* `memoize`: with pure functions, it takes advantage of referencial transparency by storing a function's arguments and the value returned by it `(memoize (fib 10))`

> "Clojure headers" preview

* `(require [something :as name])`: make easier to use `something`
* `(:gen-class)`: allows to run the program from the command line
* `(declare foo baz xii)`: allows functions to refer to those names before they are defined

> Performing side-effects on elements of a collection

`doseq` is the function for it