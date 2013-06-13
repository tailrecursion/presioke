(defproject tailrecursion/presioke "0.1.0-SNAPSHOT"
  :description "Presentation Karaoke with Clojure and ClojureScript"
  :url "https://github.com/tailrecursion/presioke"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in :leiningen
  :source-paths ["src/clj" "src/cljs"]
  :plugins      [[tailrecursion/hoplon "0.1.0-SNAPSHOT"]]
  :main tailrecursion.presioke
  :dependencies [[org.clojure/clojure     "1.5.1"]
                 [clj-http                "0.7.2"]
                 [alandipert/interpol8    "0.0.3"]
                 [ring/ring-core          "1.1.8"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [tailrecursion/hoplon    "0.1.0-SNAPSHOT"]
                 [tailrecursion/castra    "0.1.0-SNAPSHOT"]])
