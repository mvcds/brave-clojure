> Shared access to mutable state

Clojure was designed to specifically address the problems related to shared access to mutable state

> Value

A single irreducible unit in a larger system.

Values cannot be changed e.g. adding 1 to 15 does not alterate the "value" of 15.

Values are atomic

> Producing new values

A *process* applied to a value will produce a new value.

> Identity

A *name* we use to designate a succession of unchanging values produced by a process over time.

As an analogy, if _fred_ had a different phone number five years ago, it wouldn't matter how many time it has changed, we would still call those numbers "fred's phone number".

> State

The value of an identify at a point in time.

It is analog of receiving new data in the real world, just because it has changed it doesn't mean that in the past the value was not different i.e. time as a stream

> Reference type

Let you manage identities.

There are four reference types in Clojure: atom, ref, var and agent.

> Atom

`atom` creates a new atom which *refers* to its initial state.

Getting the state of an atom requires dereferencing.

> Changing an Atom's state

`swap!` applies an updater function to the current's atom's state to create a new value on the identity. It also returns the new current's atom's state.

Under the hood, `swap!` implements a *compare-and-set* semantics which basically means that if the atom's state change in the middle of an operation, it will retry to change its state based on the now current's state until it is able to set the value.

> Reset

It is possible to update an atom withouth checking its current value by using `reset!`

> Watches

A *watch* is a function which can be attached to a reference type. It gets executed when the state changes.

`(add-watch reference-type key watch-function)`

> Validators

A predicate function which executes before the reference type's state is changed. It throws an exception in negative case.

Atoms can get they validators by setting a function to the `:validator` keyword in the moment they are created.

> Ref

Allows to update the state of multiple identities using transaction semantics. These transactions are:

1. Atomic: all refs are updated or none of them
2. Consistent: all refs will have valid state
3. Isolated: the transactions behave as if they were executed serially. If two threads use the same ref, one of them will retry.

Clojure uses __software transactional memory__ (STM) to implement this transactional feature with a retrial similar to **atoms**.

A ref is created using `ref` function. And it gets altered by using `alter` withing a transaction (created by `dosync`).

It is possible to ignore the retry by usind `commute` instead of `alter`.

> Earmuffs

In lisper, __earmuff__ is name enclosed by asterisks, like `*notification-address*`

> Var

A `var` is an association between symbols and objects, they are created with `def`.

It's possible to dynamically bind them to alter their roots.

> Dynamic vars

Useful for creating global names that should refer to different values in different contexts.

A dynamic var requires the `^:dynamic` keyword and **earmuffs** (as they indicate that a symbol is intended for rebinding).

`(def ^:dynamic *notification-address* "dobby@elf.org")`

It's possible to rebind the value of a var similar to `let`

`(binding [*name* "value"] ...)`

(Aparently, `binding` does not work between threads)

Usually, dynamic vars are used to name a resource that one or more functions target (through arguments) or to configure code.

It's possible to "capture" a value within a function using `set!`, a bit like if it you were setting a value BUT to a variable outside of the function. **Kind like `out` in C#**.

When using `set!`, a way to know if a value has been captured is using `thread-bound?`.

> Altering the var root

When you create a new var, the initial value is its *root*.

It's possible to change value of the root like if we were mutating it. **This is against Clojure's philosofy!**

`(alter-var-root #'name (fn [_] "new value"))`

You can also temporarily alter a root with `with-redefs`, the var doesn't need to by dynamic. It is useful to mock values on tests, as it works across threads.

> Agent (not covered by the book)