> Macro

Allow developers to transform arbitrary expressions into valid Clojure, so it is possible to extend the language.

`defmacro` works exactly like `defn`

They are `syntatic abstractions` which allow Clojure to extend itself.

> Homoiconicity

A language is homoiconic if a programam written in it can be manipulated as data using the language itself.

> Abstract Syntax Tree (AST)

A data structure representing the program.

It serves as an input to the languages' evaluators.

> Clojure's Evaluation Model

It has three phases

* Reading: where textual source code is produced into Clojure data structures.
* Macro expansion: where macros' results are evaluated to yield forms
* Evaluating: where Clojure traverses the structures to perform actions.

In the evaluating phase, Clojure is using native data structures, which makes it homoiconic.

> [S-Expressions](http://www.gigamonkeys.com/book/syntax-and-semantics.html)

Basically, a S-Expression is a binary tree represented by lists (delimited by parentheses) and some atoms (elements, which is everything nested into the root, including nested S-Expressions).

> eval

`eval` is used to evaluate expressions

> The Reader

The textual representation of data structures is called `reader form`, by using `read-string` it is possible to convert the form to a list.

As `(str "A," "B," "C")` consists in one `str` symbol and three strings, reading it with `read-string` would produce a list with 4 items.

`(= (list? (read-string "(+ 1 2)")) true)`

```
=> (read-string "#(+ 1 %)")
; (fn* [p1__423#] (+ 1 p1__423#))
```

> Reader Macros

A set of rules for transforming text into data structures, often compacted due to an abbreviated reader form using `macro characters like "', # and @".

```
=> (read-string "'(a b c)")
; (quote (a b c))
```

> The Evaluator

It is basically a functions which takes the AST and process it using rules of that data structure's type.

Evaluation begins from the first item of the list.

* When evaluating data structures which aren't lists or symbols, the result it the data structure itself
* Symbols are resolved to either a value or special form
* Lists, when empty, evaluate to themselves. Otherwise the first element is called
    * If the call happens on a `function`, each of the other elements are considerated as its operands
    * If the call happens on a `special form`, the form will decide what to do with subsequent elements, for instance `if` only calls the truthy or falsy element

> How a macro works

A macro is a function which is executed between the reader and the evaluator, making it possible to manipulate the list which will be spitted out by the reader before the evaluator kicks in.

```
=> (defmacro ignore-last-operand
  [function-call]
  (butlast function-call))
=> (ignore-last-operand (+ 1 2 10))
```

In the example above, the argument to `ignore-last-operand` is a list and not the value `13`.

If `ignore-last-operand` was a function, it would not be possible for it to reach one of its operands and alter or ignore it.

Macro calls do not evaluate their operands.

> The return of a macro

Whereas a function's return is not evaluated, a macro's return is, in a process called `macro expansion`

It is possible to use `macroexpand` to see what data structure a macro returns before the data structure is evaluated. It is necessary to quote the form.

```
=> (macroexpand '(ignore-last-operand (+ 1 2 10)))
; (+ 1 2)
```

> The threading/stabby macro

By using the `->` macro, it is possible to pipeline data through functions

```
(defn read-resource
  [path]
  (-> path
      clojure.java.io/resource
      slurp
      read-string))
```