# presioke

This application uses Clojure and the flickr API to obtain random
images.  The function for fetching the images is exposed to
ClojureScript using [Castra RPC](https://github.com/tailrecursion/castra), and images are displayed using [Hoplon](https://github.com/tailrecursion/hoplon).

Hoplon (née HLisp) is a Leiningen plugin containing an extended ClojureScript compiler and related tools and libraries like [Javelin](https://github.com/tailrecursion/javelin).

For a rationale and some context related to this style of application development, please see [this design document](https://github.com/tailrecursion/hlisp-starter/blob/master/PROJECT.md).

Hoplon and Castra are currently under heavy development, and this demo is subject to frequent breaking change.  That said, we welcome feedback and contribution.

## Usage

    lein hoplon   # compile ClojureScript
    lein run 3000 # run a local server on port 3000
    gnome-open http://localhost:3000/index.html

Press the space bar to advance the image.

## License

Copyright © 2013 Alan Dipert

Distributed under the Eclipse Public License, the same as Clojure.
