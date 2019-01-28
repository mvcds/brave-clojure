> Macro

Allo developers to transform aribrary expressions into valid Clojure, so it is possible to extend the language.

`defmacro` works exactly like `defn`git

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

