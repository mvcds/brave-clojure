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
(defmacro better-print
 [expression]
 (list 'let ['result expression]
       (list 'println 'result)
       'result))
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

It unwraps a sequable data structure, similar to `apply` (take parenthesis out of the result).

```
=> `(+ ~(list 1 2 3))
; (clojure.core/+ (1 2 3))

=> `(+ ~@(list 1 2 3))
; (clojure.core/+ 1 2 3)
```

> gensym

A function which produces unique symbols on each call. The prefix is optional.

`(gensym 'anything)`

It is also possible to append a hash mark `(#)` to create an auto-gensym.

```
`(anything#)
```

> Macro gotchas

* **Variable capture**: when a macro introduces a binding that, unknown to the macro's user, eclipses an existing binding e.g. a `let` on the macro which name collides with an external variable. (Auto-)gensym can be used to prevent it.

* **Double evaluation**: Occurs when a form passed to a macro as an argument is evaluated more than once. This can affect how the macro renders the AST, which can have impact on code e.g. `(Thread/sleep 1000)` if evaluated 4 times, will make the Thread sleep for 4 seconds, which may be undesirable.

* **Macros All the Way Down**: As the macro expansion phase happens before evaluation, some values may be out of reach for a macro.