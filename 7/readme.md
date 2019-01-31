> Macro

Allow developers to transform arbitrary expressions into valid Clojure, so it is possible to extend the language.

`defmacro` works exactly like `defn`

> Homoiconicity

A language is homoiconic if a programam written in it can be manipulated as data using the language itself.

> Abstract Syntax Tree (AST)

A data structure representing the program.

It serves as an input to the languages' evaluators.

> Clojure's Evaluation Model

It has two phases

* Reading: where textual source code is produced into Clojure data structures.
* Evaluating: where Clojure traverses the structures to perform actions.

In the evaluating phase, Clojure is using native data structures, which makes it homoiconic.

> [S-Expressions](http://www.gigamonkeys.com/book/syntax-and-semantics.html)???

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