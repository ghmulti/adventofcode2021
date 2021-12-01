# adventofcode2021

https://adventofcode.com/2021

`Clojure CLI version 1.10.3.1029`

Local setup:
https://practical.li/clojure/clojure-tools/using-clojure-tools.html

## Usage


Run the project directly, via `:exec-fn`:

    $ clojure -X:run-x
    Day 0 to come...

Run the project, overriding the day of advent:

    $ clojure -X:run-x :day 1
    Day 1 to come...

Run the project directly, via `:main-opts` (`-m ghmulti.adventofcode2021`):

    $ clojure -M:run-m
    Select day of advent to run

Run the project, overriding the day of adfent:

    $ clojure -M:run-m 1
    Day 1 to come...

Run the project's tests (they'll fail until you edit them):

    $ clojure -T:build test

Run the project's CI pipeline and build an uberjar (this will fail until you edit the tests to pass):

    $ clojure -T:build ci

This will produce an updated `pom.xml` file with synchronized dependencies inside the `META-INF`
directory inside `target/classes` and the uberjar in `target`. You can update the version (and SCM tag)
information in generated `pom.xml` by updating `build.clj`.

If you don't want the `pom.xml` file in your project, you can remove it. The `ci` task will
still generate a minimal `pom.xml` as part of the `uber` task, unless you remove `version`
from `build.clj`.

Run that uberjar:

    $ java -jar target/adventofcode2021-0.1.0-SNAPSHOT.jar

If you remove `version` from `build.clj`, the uberjar will become `target/adventofcode2021-standalone.jar`.