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
> (chan buffer-size)
```

If you don want the take functions to block, you'd have to use sliding or dropping buffers.

With sliding buffers, itens are processed in FIFO

```
> (chan (sliding-buffer buffer-size))
```

And with dropping buffers, they are processed in LIFO

```
> (chan (dropping-buffer buffer-size))
```

> Blocking and Parking

Blocking means that a thread stops excution until a task is complete, it's heavely linked to I/O operations.

Parking frees up the main thread so it can keep doing work. It allows clojure to interleave multiplle processes in a single thread.

It's only possible to *park* from within a go block (`>!` and `<!`), while the block operations can happen anywhere (`>!, >!!, <!` and `<!!`).

> Closing a channel

The function `close!` closes a channel so it is no longer possible to put messages on it.

After all of its values are taken, a closed channel starts to returning `nil`.

> Thread

As using channels "consume" your thread pool, for operations that will take long time you can use a *thread* (rather than a *channel*) to run your process in a way that don't clog other processes.

`thread` acts almost exactly like `future`, as it creates a new thread and executes some process on it, but instead of returning a reference type, it returns a *channel* with the thread's returned value put on it.

```
> (let [t (thread "chili")]
  (<!! t))
```

> alts

Both `alts!` and `alts!!` (see "parking and blocking above") let you use the result of the first successful channel operation among a collection of operations

``
> (alts!! [c1 [c2 "put!"]])
```

In the case above, `c2` would return immediatelly, so `c1` would be ignored