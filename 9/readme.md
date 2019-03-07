> Task

Something that needs to be done

> Interleaving

Switching between tasks.

It is not necessary to complete a task before switching.

> Concurrency

Managing more than on task at the same time.

Can be achieved with interleaving.

Part of learning concurrent programming is learning to identify when chronological couplings aren’t necessary. Futures, delays, and promises allow you to separate task definition, task execution, and requiring the result.

> Parallelism

A subclass of concurrency where more than one task is executed simultaneously.

Generally achieved by simultaneously executing tasks on multiple processors.


> Distributed computing

A special version of parallel computing, where tasks are distributed on different computers

> Blocking

Waiting for a task to finish.

It most used in relation to I/O operations. 

Non-blocking operations may happen asynchronously.

> Thread

A subprogram which has its own instructions and share state to the program's state.

In a single-core processor, the threads interleave; while in multi-core processor, each thread goes through a processor.

With threads, the program beconmes **nondeterministic** because there's no guarantee of which thread will end up first (depends on the processors's velocity).

> Reference cell problem

When two or more threads can read and write to the same resource, and the value on that resource depends on the order of the writes and reads.

> Mutual exclusion problem

If instructions which change the same resource, without mutual exclusion on the permission to write on it, are interleaved the file will have a mix of both threads in it.

> Deadlock

Happens one thread is blocked by another thread waiting for its turn to access the resource.

(aka _dining philosophers problem_)

> Dereferencing

Means "requesting" the value. It will be the last expression evaluated on the `deref` macro.

It has `@` as its reader macro.

> Future

`future` define a task and place it on another thread without requiring the result immediately.

The method returns a reference value which can be used to dereference.

It is possible to know if the future has finished by using `realized?`.

They help with the mutual exclusion problem.

```
=>  (let [result (future (println "this prints once")
                         (+ 1 1))]
        (println "deref: " (deref result))
        (println "@: " @result))
; "this prints once"
; deref: 2
; @: 2
```

Notice that the string "this prints once" indeed prints only once, even though you dereference the future twice. This shows that the future’s body ran only once and the result, 2, got cached.

As a function, it is possible to limit how long `deref` should wait.

```
=> (deref (future (Thread/sleep 1000) 0) :limit :time-out-value)
; 5
```

> Delay

`delay` can be used to define a task without having to execute it or require the result immediately.

It can be derefereced or forced through `force`.

A delay is executed only once so they help with the mutual exclusion problem.

> Promise

`promise` allows to express that an result is expected, without having to define the task that should produce it or when that task should run.

To bind the result to a promise it's necessary to `deliver`, and to get itto use dereferencing.

As it is not possible to override a delivered value, promisses help with te reference cell problem.

It's possible to use `deref` to set a timeout.