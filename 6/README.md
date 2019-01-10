> Namespace

Contain a map between human-friendly *symbols* and their references internally

They are of type `clojure.lang.Namespace` and are a data structure like any other in Clojure

In Clojure programs you are always in a namespace, and variables are local to the namespaces they were created into

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