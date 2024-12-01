#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn distance [numbers]
  (let [converted-numbers (map #(Integer/parseInt %) numbers)]
    (Math/abs (apply - converted-numbers))))

(defn transpose [m]
  (apply map list m))

(defn sort-lists [lists]
  (->> lists
       (transpose)
       (map sort)
       (transpose)))

(defn parse-line [line]
  (str/split line #"\s+"))

(->> (slurp "data.txt")
     (str/split-lines)
     (map parse-line)
     (sort-lists)
     (map distance)
     (apply +)
     (println))
