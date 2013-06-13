# presioke

This application uses the Clojure and the flickr API to obtain random
images.  The function for fetching the images is exposed to
ClojureScript using Castra, where images are rendered using Hoplon and
Javelin.

## Usage

    lein hoplon # compile ClojureScript
    lein run    # run a local server
    gnome-open http://localhost:3000/index.html

Press any key to advance the images.

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
