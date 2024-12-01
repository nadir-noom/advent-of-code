#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn frequency [[first-list second-list]]
  (let [frequency-map (group-by identity second-list)]
    (for [num first-list]
      (* (Integer/parseInt num)
         (count (get frequency-map num []))))))

(defn transpose [m]
  (apply map list m))

(defn parse-line [line]
  (str/split line #"\s+"))

(->> (slurp "data.txt")
     (str/split-lines)
     (map parse-line)
     (transpose)
     (frequency)
     (apply +)
     (println))
