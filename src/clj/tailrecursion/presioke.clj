(ns tailrecursion.presioke
  (:require
   [ring.adapter.jetty           :refer [run-jetty]]
   [ring.middleware.resource     :refer [wrap-resource]]
   [ring.middleware.file         :refer [wrap-file]]
   [ring.middleware.file-info    :refer [wrap-file-info]]
   [tailrecursion.castra.handler :refer [castra]]))

(def app
  (->
   (castra '[tailrecursion.presioke.flickr :only [get-interesting]])
   (wrap-resource "public")
   (wrap-file-info)))

(defn -main
  [& args]
  (run-jetty app {:port 3000}))
