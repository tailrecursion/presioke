(defproject tailrecursion/presioke "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main tailrecursion.presioke
  :source-paths ["src/clj" "src/cljs"]
  :plugins      [[tailrecursion/hoplon "0.1.0-SNAPSHOT"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-http "0.7.2"]
                 [alandipert/interpol8 "0.0.3"]
                 [ring/ring-core          "1.1.8"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [tailrecursion/castra    "0.1.0-SNAPSHOT"]])
