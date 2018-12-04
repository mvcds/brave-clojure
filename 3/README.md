
> All clojure is written in a uniform structure, a valid code is known as **form**

```clj
(operator operands)
```
* Literal i.e. numbers, strings, map, vectors, etc
* Operation

> Falsy values are **false** and **nil**

* or => either 1st truthy or last value
* and => first falsy or last truthy

> coersion & contagion (?)

> Basic structures
* Maps `{}` are like dictionaries/hashes
* Keywords `:token` can be used as functions
* Vector `[]` are like arrays
    * Use when appending things to the end
    * `(conj [1 2 3] 4)` => `[1 2 3 4]`
* List `'()` are like arrays
    * Use when writting macros or appending things to the beginning
    * `(conj '(1 2 3) 4)` => `(4 1 2 3)` 
* Set `#{}` are used for unique values

> Special forms unlike function calls

* might not evaluate all of their operands
* can't be used as functions arguments

Macros are special forms which evaluate operands differently from function calls

> Functions

```clj
(defn name
    "docstring"
    [params]
    (body))
```
**Functions return the last form evaluated**. They support **arity overloading** and accept **rest parameteers** which must be the last argument  `[first second & third]`, in this case `third` is the rest, transformed into a list.

**Destructing** allows to bind names to values withing colletions

> Anonymous functions

```clj
(fn [params] body)
#(operator %)
#(operator %1 %2 %n)
```

> `let` 

`let name value` allows us to bind a name to a value, the advantage of doing so is that **the expression is evaluated only once** 

> `loop`

```clj
(loop [name value]
    (...
        (recur x))
```

Using `recur` is **more optimistic** than calling the function by its name

> Regular expressions

`#expression`