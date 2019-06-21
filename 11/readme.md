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