> Task

Something that needs to be done

> Interleaving

Switching between tasks.

It is not necessary to complete a task before switching.

> Concurrency

Managing more than on task at the same time.

 be achieved with interleaving.

> Parallelism

A subclass of concurrency where more than one task is executed simultaneasly.

Generally achieved by simultaneously executing tasks on multiple processors.

> Distributed computing

A spectial version of parallel computing, where tasks are distributed on different compiters

> Blocking

Waiting for a task to finish.

It most used in relation to I/O operations. 

Non-blocking operatoions may happen asynchronously.

> Thread

A subprogram which has its own instructions and share state to the program's state.

In a single-core processor, the threads interleave; while in multi-core processor, each thread goes through a processor.

With threads, the program beconmes **nondeterministic** because there's no guarantee of which thread will end up first (depends on the processors's velocity).

> Reference cell problem

When two or more threads can read and write to the same location, and the value on that location depends on the order of the writes and reads.

> Mutual exclusion problem

If instructions which change the same file, without mutual exclusion on the prermission to write on it, are interleaved, the file will have a mix of both threads in it.

> Deadlock

Happens one thread is blocked by another thread.

(aka _dining philosophers problem_)
