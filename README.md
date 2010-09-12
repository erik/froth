#froth
##A small twist of forth

froth is a small implementation of the Forth programming language, written in
Clojure. It's not fully finished, and is the product of a few hours of messing 
around.

Running (require Leiningen):
*  `git clone git://github.com/boredomist/froth.git`
*  `cd froth`
*  `lein deps`
*  `./froth`

Examples

    > 1 2 + .
    3

    > 1 2 / .
    1/2

    > 1. 2. / .
    0.5

    > .S
    []
    > 1 2 3 4 5 .S
    [1 2 3 4 5]

And so on.
