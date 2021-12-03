(ns star-2.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn oxygen-matcher [freq]
  (if (> (get freq \0 0) (get freq \1 0)) \0 \1))

(defn co2-matcher [freq]
  (if (<= (get freq \0 0) (get freq \1 0)) \0 \1))

(defn bin->dec [input]
  (let [bits (map #(Integer/parseInt (apply str %)) (partition 1 input))
        pow (reverse (take (count input) (iterate #(* 2 %) 1)))]
    (apply + (map #(apply * %) (partition 2 (interleave bits pow))))))

(defn filter-input [input matcher]
  (loop [values input
         position 0]
    (let [freq (frequencies (map #(nth % position) values))
          new-values (filter #(= (nth % position) (matcher freq)) values)]
      (if (> (count new-values) 1)
        (recur new-values (inc position))
        (bin->dec (first new-values))))))

(defn -main [input]
  (->> input
       (#(* (filter-input % oxygen-matcher) (filter-input % co2-matcher)))
       (println)))
