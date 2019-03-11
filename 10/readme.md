> Shared access to mutable state

Clojure was designed to specifically address the problems related to shared access to mutable state

> Value

A single irreducible unit in a larger system.

Values cannot be changed e.g. adding 1 to 15 does not alterate the "value" of 15.

> Producing new values

A process applied to a value will produce a new value.

> Identity

A *name* we use to designate a succession of unchanging values produced by a process over time.

As an analogy, if _fred_ had a different phone number five years ago, it wouldn't matter how many time it has changed, we would still call those numbers "fred's phone number".

> State

The value of an identify at a point in time.

It is analog of receiving new data in the real world, just because it has changed it doesn't mean that in the past the value was not different i.e. time as a stream

> Reference type

Let you manage identities.

There are four reference types in Clojure: atom, ref, var and agent (this last one will not be covered).

> Atom

`atom` creates a new atom which *refers*  to its initial value.

Getting the state of an atom requires dereferencing.

> Changing an Atom's state

`swap!` applies an updater function to the current's atom's state to create a new value on the identity.

`swap!` also returns the new current's atom's state.

Under the hood, `swap!` implements a *compare-and-set* semantics which basically means that if the atom's state change in the middle of an operation, it will retry to change its state based on the now current's state.

> Reset

It is possible to update an atom withouth checking its current value by using `reset`

> Watches

A *watch* is a function which can be attached to a reference type. It gets executed when the state changes.

`(add-watch reference-type key watch-function)`

> Validators

A predicate function which executes before the reference type's state is changed. It throws an exception in negative case.

Atoms can get they validators by setting a function to the `:validator` keyword in the moment they are created.
