# presioke

This application uses the Clojure and the flickr API to obtain random
images.  The function for fetching the images is exposed to
ClojureScript using Castra, where images are rendered using Hoplon and
Javelin.

## Usage

    lein hoplon   # compile ClojureScript
    lein run 3000 # run a local server on port 3000
    gnome-open http://localhost:3000/index.html

Press the space bar to advance the image.

## License

Copyright © 2013 Alan Dipert

Distributed under the Eclipse Public License, the same as Clojure.
