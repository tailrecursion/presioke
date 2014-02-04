# presioke

This ClojureScript application uses [Hoplon](http://hoplon.io) and the flickr API to
obtain random images and display them in the browser.  [Try it](http://alandipert.github.io/presioke/) or [see all of the code](https://github.com/tailrecursion/presioke/blob/master/src/index.cljs.hl).

## Dependencies

- java 1.7+
- [boot][1]

## Development

1. Start the auto-compiler and get audio compiler feedback:

```bash
$ boot watch hear [hoplon {:prerender false}]
```

2. Open the compiled html file.

```bash
$ cd resources/public
$ open index.html
```

## Compile for Production

```bash
$ boot prod
```

[1]: https://github.com/tailrecursion/boot

## License

Copyright © 2014 Alan Dipert

Distributed under the Eclipse Public License, the same as Clojure.
