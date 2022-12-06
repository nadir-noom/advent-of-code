#!/usr/bin/env bb
(require '[clojure.set :as set])

(def start-packet-size 14)

(defn start-of-transmission [signal]
  (for [x (range (count signal))
        :let [packet (set (nth signal x))]
        :when (= start-packet-size (count packet))]
    (+ x start-packet-size)))

(->> (slurp "data.txt")
     (str/trim)
     (partition start-packet-size 1)
     (start-of-transmission)
     (first))
