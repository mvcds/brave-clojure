> Clojure and Java

1. Clojure applications are run the same way as Java applications.
2. Java is necessary for core functionalities of Clojure like reading files and working with dates.
3. Java already has a vast ecosystem of useful libraries and Clojure can benefit from using them.

> The JVM

Your machine executes machine instructions regardless of the language you've used to write your program.

The same way, the Java Virtual Machine (JVM) executes bytecode by translating it on the fly into machine code that the host understand throught a process called _just-in-time compilation_.

Java and Clojure programs are compiled to Java bytecode, packed in JAR files.

> Java interop

Writing Clojure code which uses Java classes, objects and methods directly

> Java commands

When you have a `.java` file, the command `javac` will create a file with the `.class` associated to that file, which is executable with `java`

```
> ls
# MyOwnTest.java
> javac MyOwnTest.java
> ls
# MyOwnTest.class MyOwnTest.java
> java MyOwnTest
# "Hello World!"
```

Running `java` makes the JVM look at your _classpath_ for a class named after the file, in this case `MyOwnTest`. THe classpath is the list of filesystem paths that the JVM searchs to find a file that defines a class.

Pacakges in Java organize code and require a matching directory structure due to the classpath, which defaults for the current folder when running `javac`. If you want to start from a subfolder, the flag `-classpath` should be suplied to the root of the correct path.

> JAR files

All your `.class` files can be bundled together into a single file with the java archive extension (JAR). JAR files are actually only ZIP files.

```
> jar cvfe bundle-name.jar EntryPoint EntryPoint.class folders/*.class
# added manifest
# ...
> java -jar bundle-name.jar
```

The flag `e` needs to be suplied with the class which will be used as an entry point - the class that contains the `main` method.

JAR files have all its classpaths internalized, so it's possible to execute them from anywhere.