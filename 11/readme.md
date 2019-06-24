> core.async

A library that allows you to create multiple independent processes withing a single program

> Event interpretation

Basically, everything can be interpretented as different entities concurrently responding to events in the world if we define real-world objects as the sum of their event-driven behaviour.

> Process

A concurrently running unit of logic that respond to events.

You create a process with the funtion `go`, which creates a "go block" that runs concurrently on a separate thread.

```
> (def (echo-chan (chan)))
> (go (println (<! echo-chan)))
```

Processes wait to receive messages and block until they have their messages taken.

> Go block's threads

The processes of a go block are run on a thread pool that contains the number of threads equal to `2 + how many cores your machine has`.

This way your program doesn't have to create a thread per process, resulting in better performance because the overhead of creating threads is abstracted away from the developer.

> Channel

They communicate *messages* and are created with the funcion `chan`

It's possible to *put* messages on a channel and *take* messages off a channel. And a process will wait for the completion of those put and/or take events.

> Take

The functions `<!` and `<!!` are used to express that a message is taken from a channel

```
> (go (println (<! echo-chan)))
; "when i take a message from the echo-chan channel, print it"
```

Take functions start listening to a channel and the process it belongs to waits until another process puts a message on the channel.

> Put

The functions `>!` and `>!!` put a message on a channel, returning `true` if it was passsed succesfully.

Putting a message on a channel blocks the process until another process take the message.

> Buffers

The regular buffer size of a channel is 1, but it's possible to define its size.

```
(chan buffer-size)
```

If you don want the take functions to block, you'd have to use sliding or dropping buffers.

With sliding buffers, itens are processed in FIFO

```
(chan (sliding-buffer buffer-size))
```

And with dropping buffers, they are processed in LIFO

```
(chan (dropping-buffer buffer-size))
```

> Blocking and Parking

Blocking means that a thread stops excution until a task is complete, it's heavely linked to I/O operations.

Parking frees up the main thread so it can keep doing work. It allows clojure to interleave multiplle processes in a single thread.

It's only possible to *park* from within a go block (`>!` and `<!`), while the block operations can happen anywhere (`>!, >!!, <!` and `<!!`).

> Thread

There are definitely times when you’ll want to use blocking instead of parking, like when your process will take a long time before putting or taking, and for those occasions you should use `thread`, it acts almost exaclty like `future`: it creates a new thread and executes a process on that thread, though it returns a channel, not a reference type.

When a `thread`'s process stops, the process's return value is put on the channel that the thread returns

```
(let [t (thread "chili")]
  (<!! t))
```

The reason you should use `thread` instead of a go block when you’re performing a long-running task is so you don’t clog your thread pool.

> alts

Both `alts!` and `alts!!` (see "parking and blocking above") let you use the result of the first successful channel operation among a collection of operations

``
(alts!! [c1 [c2 "put!"]])
```

In the case above, `c2` would return immediatelly, so `c1` would be ignored