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

(deftask prod
  "Compile the application in advanced mode."
  [& args]
  (hoplon {:optimizations :advanced
           :prerender true}))

(deftask hack!
  "Continuously compile the application and skip prerender."
  [& args]
  (comp (watch) (hoplon {:prerender false})))
