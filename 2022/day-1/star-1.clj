#!/usr/bin/env bb
(require '[clojure.string :as str])

(defn ->int [value]
    (Integer/parseInt value))

(->> (slurp "data.txt")
     (str/split-lines)
     (partition-by str/blank?)
     (filter #(not (some  #{""} %)))
     (map #(map ->int %))
     (map #(apply + %))
     (apply max))
