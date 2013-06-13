(ns tailrecursion.presioke.flickr
  (:require [clj-http.client      :as    http]
            [cheshire.core        :refer [parse-string]]
            [alandipert.interpol8 :refer [interpolating]]))

(defn flickr
  [method params]
  (assert (contains? params "api_key"))
  (let [response (http/get "http://api.flickr.com/services/rest/"
                           {:query-params
                            (merge
                             params
                             {"method" method
                              "format" "json"
                              "nojsoncallback" 1})})]
    (-> response :body parse-string)))

(defn make-url
  [{:strs [farm server id secret]}]
  (interpolating
   "http://farm#{farm}.staticflickr.com/#{server}/#{id}_#{secret}.jpg"))

(defn get-interesting
  [api-key]
  (let [photos (-> "flickr.interestingness.getList"
                   (flickr {"api_key" api-key "per_page" 500})
                   (get-in ["photos" "photo"]))]
    (mapv make-url photos)))
