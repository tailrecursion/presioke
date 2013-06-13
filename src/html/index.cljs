(ns tailrecursion.presioke.index
  (:require-macros
   [tailrecursion.hoplon.macros  :refer [reactive-attributes]]
   [tailrecursion.javelin.macros :refer [cell]])
  (:require
   tailrecursion.javelin
   [tailrecursion.castra :refer [async]]
   [tailrecursion.hoplon.reactive :as d]))

(def images
  (cell '["http://media.desura.com/cache/images/members/1/365/364567/thumb_940x3000/welcome_to_the_internet.jpg"]))

(def cursor (cell 0))

(def current-image (cell (get images cursor)))

(def api-key "d4fbe84122c1fb2c58dcdd974f5e46ef")

(async ["tailrecursion.presioke.flickr/get-interesting" api-key]
       #(swap! images into %))

(.on (js/jQuery "body") "keypress" #(swap! cursor inc))

(html
 (head (title "Presioke"))
 (body
  (reactive-attributes
   (img {:do [(d/attr! :src current-image)]}))))
