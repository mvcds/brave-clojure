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

* Reference cell problem: When two or more threads can read and write to the same resource, and the value on that resource depends on the order of the writes and reads.

* Mutual exclusion problem: If instructions which change the same resource, without mutual exclusion on the permission to write on it, are interleaved the file will have a mix of both threads in it.

*Deadlock aka _dining philosophers problem_: Happens when one thread is blocked by another thread waiting for its turn to access the resource.

> Dereferencing

Means "requesting" the value. It will be the last expression evaluated on the `deref` macro.

It has `@` as its reader macro.

As a function, it is possible to limit how long `deref` should wait.

```
=> (deref (future (Thread/sleep 1000) 0) :timeout-milliseconds :time-out-value)
; 5
```

> Future

`future` define a task and execute it on another thread without requiring the result immediately.

The method returns a reference value which can be used to dereference.

It is possible to know if the future has finished by using `realized?`.

They help with the mutual exclusion problem.

```
=>  (let [value (future (println "this prints once")
                         (+ 1 1))]
        (println "deref: " (deref value))
        (println "@: " @value))
; "this prints once"
; deref: 2
; @: 2
```

Notice that the string "this prints once" indeed prints only once, even though you dereference the future twice. This shows that the future’s body ran only once and the returned value got "cached".

> Delay

`delay` can be used to define a task without having to execute it or require the result immediately.

It can be derefereced or forced through `force` or `@`.

A delay is executed only once so they help with the mutual exclusion problem.

```
=>  (def jackson-5-delay
      (delay (let [message "Just call my name and I'll be there"]
               (println "First deref:" message)
               message)))
; #'user/jackson-5-delay

=> (force jackson-5-delay)
; First deref: Just call my name and I'll be there
; "Just call my name and I'll be there"

=> (force jackson-5-delay)
; "Just call my name and I'll be there"
```

As it can be seen by the second result, the returned value is also "cached"

> Promise

`promise` allows to express that an result is expected, without having to define the task that should produce it or when that task should run.

To bind the result to a promise it's necessary to `deliver`, and to get its value, use dereferencing.

As it is not possible to override a delivered value, promisses help with te reference cell problem.

```
=> (def my-promise (promise))
=> (deliver my-promise (+ 1 2))
=> @my-promise
; 3
```

It's possible to use promises as if they were JavaScript's callbacks

```
=> (let [ferengi-wisdom-promise (promise)]
      (future (println "Here's some Ferengi wisdom:" @ferengi-wisdom-promise))
      (Thread/sleep 500)
      (deliver ferengi-wisdom-promise "Whisper your way to success."))
; Here's some Ferengi wisdom: Whisper your way to success.
```