> Namespace

Contain a map between human-friendly *symbols* and their references internally

They are of type `clojure.lang.Namespace` and are a data structure like any other in Clojure

In Clojure programs you are always in a namespace, and variables are local to the namespaces they were created into

By default, a REPL starts in the `user` namespace and refers to `clojure.core`

`(clojure.core/refer-clojure)` is a practical way to refer to `clojure.core` namespace

Namespaces are not bound automatically, it is necesary to tell Clojure which namespaces you want to use, even when they are in the project.

> Symbols

Data types within Clojure

It is possible to refer to the symbol itself rathar than the value it represents by quoting

```bash
=> inc
; #<core$inc clojure.core$inc@30132014>

=> 'inc
; inc

=> (map inc [1 2])
; (2 3)

=> '(map inc [1 2])
; (map inc [1 2])
```

> Interning

The process of giving a *symbol* something to represent

`def` is the primary tool for storing objects in clojure. It returns a var

```bash
(def primes [1 2 3 5])
=> #'user/primes
```

> Name collision

When a symbol is given the same of a symbol which already exists, this collision erases the first symbol forever

> Creating namespaces

* `create-ns`: takes a symbol, creates the namespace (if it doesn't exist) and returns it `(create-ns 'test)`
* `in-ns`: same as `create-ns` but switches to it `(in-ns 'test)`

> Refer

Allows to refer to the corresponding namespace's objects without having to fully use fully qualified symbols

```bash
=> (clojure.core/refer 'cheese.taxonomy)
=> bries ; though the symbols is defined in a different namespace, it becomes accessible
```

It is possible to apply filters over `refer`

* `:only`: gets a vector of the symbols to be imported `(clojure.core/refer 'cheese.taxonomy :only ['bries])`
* `:exclude`: gets a vector of the symbols to be ignored `(clojure.core/refer 'cheese.taxonomy :exclude ['bries])`
* `:rename`: gets a map of the symbols to be renamed `(clojure.core/refer 'cheese.taxonomy :rename {'bries 'best-kind-of-cheese})`

> Private functions

By using `defn-` to create a function, it becomes private to that namespace and thus is not exported when the namespace is refered even if its name is explicillty mentioned

It is still possible to access it by using `@#'some/private-var`

> Alias

"Rename" a namespace reference locally, so it is easier to refer to it `(clojure.core/alias 'taxonomy 'cheese.taxonomy)`

> Name conventions

When using `lein`, underscores in the filesystem correspond to dashes in the namespace.

And talking about the filesystem, the path is shown by `.` (periods), `directory-separated-by-dashes.sub-directory.name-of-file-with-no-extension`

> Require

Read and evaluate the contents of a namespace, you can use `:as` as a way to use alias.

`(require '[the-divine-cheese-code.visualization.svg :as svg])`

> Use

Through `use`, it is possible to `require` and `refer` automatically

`:as` also works with use

`use` is frowned upon in production

If you use `use` with a vector, the first item is the base, and the rest are symbols within that base => `(:use [clojure.java browse io])`