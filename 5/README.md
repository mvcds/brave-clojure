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