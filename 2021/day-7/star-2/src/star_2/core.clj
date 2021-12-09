(ns star-2.core
  (:require [clojure.string :as str]
            [clojure.walk :refer [prewalk]]
            [clojure.core.matrix :as matrix])
  (:gen-class))

(defn cost-to-x [crabs x]
  (apply + (map #(apply + (range 1 (inc (- (max % x) (min % x))))) crabs)))

(defn align-crabs [crabs]
  (let [min-pos (apply min crabs)
        max-pos (apply max crabs)]
    (apply min (map #(cost-to-x crabs %) (range min-pos (inc max-pos))))))

(defn parse-input [input]
  (->> input
       (first)
       (#(str/split % #","))
       (map #(Integer/parseInt %))))

(defn -main [input]
  (->> input
       (parse-input)
       (align-crabs)
       (println)))
