> Macro arguments

Functions get all their arguments evaluated before entering the function; macros get them unevaluated.

> Symbols x Values

In the following invalid form, the macro tries to get the _values_ of the _symbols_ `let`, `result` and `println`; throwing an exception.

```
(defmacro better-print
 [expression]
 (list let [result expression]
       (list println result)
       result))
```

To make it work, you should quote the values as a way to _turn off_ evaluation for the symbols, with either `quote` or its reader macro (`'`).

> Quote (reader macro)

Turns off evaluation of a symbol, so the final list used by the macro keeps the symbol