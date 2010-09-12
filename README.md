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

    Copyright (c) 2010 Erik Price

     Permission is hereby granted, free of charge, to any person obtaining a copy
     of this software and associated documentation files (the "Software"), to deal
     in the Software without restriction, including without limitation the rights
     to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
     copies of the Software, and to permit persons to whom the Software is
     furnished to do so, subject to the following conditions:
    
     The above copyright notice and this permission notice shall be included in
     all copies or substantial portions of the Software.

     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
     THE SOFTWARE.

