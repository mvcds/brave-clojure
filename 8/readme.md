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

Turns off evaluation of a symbol, so the final list used by the macro keeps the symbol.

Quoting recursively quotes all the elements.

```
=> '(+ 1 2)
; (+ 1 2)
```

> Syntax Quoting

Returns the *fully qualified* symbol i.e. includes symbol's namespace, thus making harder to avoid name collisions.

```
=> +
; #function[clojure.core/+]

=> '+
; +

=> 'clojure.core/+
; 'clojure.core/+
```

Syntax quoting recursively syntax quotes all elements

```
=> `(+ 1 2)
; (clojure.core/+ 1 2)
```

> Unquote forms

Deactivates syntax quoting, so that the expression is evaluated instead of being quoted.

```
=> `(+ 1 (inc 1))
; (clojure.core/+ 1 (clojure.core/inc 1))

=> `(+ 1 ~(inc 1))
; (clojure.core/+ 1 2)
```

> Quoting and Unquoting

Think about them as string interpolation, but instead they turn of and on the evaluator, allowing to make code clear. Compare:

```
=> (list '+ 1 (inc 1))
; (+ 1 2)

=> `(+ 1 ~(inc 1))
; (clojure.core/+ 1 2)
```

> Unquote splicing

It unwraps a sequable data structure, similar to `apply`.

```
=> `(+ ~(list 1 2 3))
; (clojure.core/+ (1 2 3))

=> `(+ ~@(list 1 2 3))
; (clojure.core/+ 1 2 3)
```