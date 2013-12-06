(ns tailrecursion.presioke.index
  (:require-macros
   [tailrecursion.hoplon.macros  :refer [reactive-attributes]]
   [tailrecursion.javelin.macros :refer [cell]]
   [alandipert.interpol8         :refer [interpolating]])
  (:require
   tailrecursion.javelin
   [tailrecursion.hoplon.reactive :as d]))

;;; flickr API

(defn flickr
  "Invokes the flickr API method with params and calls the success
  function."
  [method params success]
  (->> {"type" "GET"
        "url" "http://api.flickr.com/services/rest/"
        "data" (merge params {"method" method, "format" "json"})
        "jsonpCallback" "jsonFlickrApi"
        "dataType" "jsonp"
        "success" (comp success js->clj)}
       clj->js
       (.ajax js/jQuery)))

(defn image-url
  "Converts a flickr image response map into an image URL."
  [{:strs [farm server id secret]}]
  (interpolating
   "http://farm#{farm}.staticflickr.com/#{server}/#{id}_#{secret}.jpg"))

;;; cells

(def images
  "Stem cell. Vector of image URLs. Contains an initial default image."
  (cell '["http://www.auburn.edu/~burnsma/peopl96a.gif"]))

(def cursor
  "Input cell. Index of the current image URL."
  (cell 0))

(def ready?
  "Formula cell. True when images remain. Accounts for the default image."
  (cell (> (dec (count images)) cursor)))

(def current-image
  "Formula cell. URL of the currently selected image."
  (cell (get images cursor)))

(def image-style-base
  [[:background-size "cover"]
   [:position "fixed"]
   [:top "0"]
   [:bottom "0"]
   [:left "0"]
   [:right "0"]
   [:z-index "1"]])

(defn inline-style-map
  [map]
  (apply str (interpose " "
                        (for [[k v] map]
                          (str (name k) ": " v ";")))))


(def current-image-style
  "creates background style on the body element with the image-url value"
  (cell (-> [:background (format "url(%s)" current-image)]
            (cons image-style-base)
            inline-style-map)
                                        ;(format "background: url(%s); background-size: cover; position: fixed; top: 0; bottom: 0; left: 0; right: 0; z-index: 1;" current-image)
   ))

;;; style maps

(def page-marker-style
  {:z-index "2"
   :position "absolute"
   :background "rgba(0,0,0,0.5)"
   :border-radius "30px"
   :color "#fff"
   :padding "10px"})

(def helper-text-style
  {:z-index "2"
   :position "absolute"
   :background "rgba(0,0,0,0.5)"
   :border-radius "30px"
   :color "#fff"
   :bottom "0"
   :left "0"
   :right "0"
   :text-align "center"
   :padding "10px"})

(defn inline-style-map
  [map]
  (apply str (interpose " " (for [[k v] map]
  (str (name k) ": " v ";")))))

;;; initialize

(.on (js/jQuery "body") "keypress click" #(swap! cursor inc))

(flickr "flickr.interestingness.getList"
        {"api_key" "d4fbe84122c1fb2c58dcdd974f5e46ef", "per_page" 500}
        #(swap! images into (map image-url (get-in % ["photos" "photo"]))))

(html
 (head
   (title "Presioke"))
 (body
  (reactive-attributes
   (div {:do [(d/attr! :style current-image-style)]})
   (span {:do [(d/text! (if ready? (str (inc cursor) "/" (count images)) "Loading..."))
               (d/attr! :style (inline-style-map page-marker-style))]})
   (br)
   (span {:do [(d/text! (if ready? "Press a key or click the mouse for a new image."))
               (d/attr! :style (inline-style-map helper-text-style))]}))))
