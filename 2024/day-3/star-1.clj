#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn mul[command]
  (apply * (map #(Integer/parseInt %) (re-seq #"\d+" command))))

(defn extract-commands [line]
  (re-seq #"mul\([0-9]{1,3},[0-9]{1,3}\)" line))

(->> (slurp "data.txt")
     (str/split-lines)
     (mapcat extract-commands)
     (map mul)
     (apply +)
     (println))
