#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.0.0"

(set-env!
 :project
 'tailrecursion/presioke
 :version
 "0.1.0-SNAPSHOT"
 :dependencies
 '[[tailrecursion/boot.core "2.0.0"]
   [tailrecursion/boot.task "2.0.0"]
   [tailrecursion/hoplon "5.1.1"]
   [org.clojure/clojurescript "0.0-2138"]]
 :src-paths
 #{"src"}
 :out-path
 "resources/public")

(add-sync! (get-env :out-path) #{"resources/assets"})

(require
 ['tailrecursion.hoplon.boot :refer :all]
 ['tailrecursion.boot.task :refer :all])

(deftask prod
  "Compile the application in advanced mode."
  [& args]
  (boot [hoplon {:optimizations :advanced
                 :prerender true}]))

(deftask hack!
  "Continuously compile the application and skip prerender."
  [& args]
  (boot watch [hoplon {:prerender false}]))
