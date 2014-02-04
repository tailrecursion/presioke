(ns tailrecursion.boot.task.notify
  (:require [clojure.java.io         :as    io]
            [cljs.analyzer           :refer [with-warning-handlers]]
            [tailrecursion.boot.core :refer [deftask]]
            [clojure.java.shell      :refer [sh]]))

(defn play! [file]
  (-> file
      io/resource
      io/file
      java.io.FileInputStream.
      java.io.BufferedInputStream.
      javazoom.jl.player.Player.
      .play
      future))

(defn warn! [n]
  (let [msg (str n "warning" (if (> n 1) "s"))]
    (cond
     (.exists (io/file "/usr/bin/espeak"))
     (future (sh "espeak" "-v+f2" msg))
     (.exists (io/file "/usr/bin/say"))
     (future (sh "say" "-v" "Vicki" msg))
     :else (play! "warning.mp3"))))

(deftask notify-sound
  [& args]
  (fn [continue]
    (fn [event]
      (try (let [warnings (atom 0)
                 ret (with-warning-handlers
                       [@#'cljs.analyzer/default-warning-handler
                        (fn [& _] (swap! warnings inc))]
                       (continue event))]
             (if (zero? @warnings) (play! "success.mp3") (warn! @warnings))
             ret)
           (catch Throwable t
             (play! "failure.mp3")
             (throw t))))))
