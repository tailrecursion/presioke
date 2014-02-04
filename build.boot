#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.0.0"

(set-env!
 :project      'tailrecursion/presioke
 :version      "0.1.0-SNAPSHOT"
 :dependencies (read-string (slurp "deps.edn"))
 :src-paths    #{"src"}
 :out-path     "resources/public")

(add-sync! (get-env :out-path) #{"resources/assets"})

(require
 ['tailrecursion.hoplon.boot :refer :all]
 ['tailrecursion.boot.task :refer :all])

(defn play!
  [file]
  (set-env! :dependencies '[[javazoom/jlayer "1.0.1"]])
  (require '[clojure.java.io :as io])
  (future
    (eval
     `(let [bis# (-> (~'io/resource ~file)
                     ~'io/file
                     java.io.FileInputStream.
                     java.io.BufferedInputStream.)]
        (.play (javazoom.jl.player.Player. bis#))))))

(deftask notify-sound
  [& args]
  (fn [continue]
    (fn [event]
      (try (let [ret (continue event)]
             (play! "success.mp3")
             ret)
           (catch Throwable t
             (play! "failure.mp3")
             (throw t))))))

(deftask nrepl
  [& [{:keys [port] :or {port 0}}]]
  (fn [continue]
    (fn [event]
      (set-env! :dependencies '[[org.clojure/tools.nrepl "0.2.3"]])
      (require '[clojure.tools.nrepl.server :refer [start-server]])
      (let [server ((resolve 'start-server) :port port)]
        (println "Started nREPL server on port" (:port server))
        (spit ".nrepl-port" (:port server))
        (.deleteOnExit (java.io.File. ".nrepl-port"))
        (continue event)
        @(promise)))))

(deftask prod
  "Compile the application in advanced mode."
  [& args]
  (hoplon {:optimizations :advanced
           :prerender true}))

(deftask hack!
  "Continuously compile the application and skip prerender."
  [& args]
  (comp (watch) (notify-sound) (hoplon {:prerender false})))
