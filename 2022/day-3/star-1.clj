#!/usr/bin/env bb
(require '[clojure.set :as set])

(defn char->value [char]
  (if (Character/isUpperCase char)
    (- (int char) 38)
    (- (int char) 96)))

(->> (slurp "data.txt")
     (str/split-lines)
     (map #(split-at (/ (count %) 2) %))
     (map #(vector (set (first %)) (set (second %))))
     (map #(apply set/intersection %))
     (map first)
     (map char->value)
     (apply +))
