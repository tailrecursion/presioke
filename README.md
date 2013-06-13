# presioke

This application uses the Clojure and the flickr API to obtain random
images.  The function for fetching the images is exposed to
ClojureScript using Castra, where images are rendered using Hoplon and
Javelin.

## Usage

### Deployment to Heroku

See [Building a Database-Backed Clojure Web
Application](https://devcenter.heroku.com/articles/clojure-web-application)
for instructions on installing and configuring the Heroku tools and
deploying database-backed Clojure applications.

Then, deploy this application as per the article's
[Deploy](https://devcenter.heroku.com/articles/clojure-web-application#deploy)
instructions.

### Running Locally

If you are running PostgreSQL locally, you may run the application on
your machine with a command like:

    foreman start

## License

Copyright © 2013 Alan Dipert

Distributed under the Eclipse Public License, the same as Clojure.
