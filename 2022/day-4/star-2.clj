#!/usr/bin/env bb
(require '[clojure.set :as set])

(defn overlapping-assignments? [[elf-1 elf-2]]
  (not-empty (set/intersection elf-1 elf-2)))

(defn parse-elf [elf]
  (let [[start last] (map #(Integer/parseInt %) (str/split elf #"-"))]
    (set (range start (inc last)))))

(->> (slurp "data.txt")
     (str/split-lines)
     (map #(str/split % #","))
     (map #(map parse-elf %))
     (filter overlapping-assignments?)
     (count))
