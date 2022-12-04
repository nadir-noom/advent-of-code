#!/usr/bin/env bb
(require '[clojure.set :as set])

(defn overlapping-assignments? [[elf-1 elf-2]]
  (or (set/superset? elf-1 elf-2)
      (set/superset? elf-2 elf-1)))

(defn parse-elf [elf]
  (let [[start last] (map #(Integer/parseInt %) (str/split elf #"-"))]
    (set (range start (inc last)))))

(->> (slurp "data.txt")
     (str/split-lines)
     (map #(str/split % #","))
     (map #(map parse-elf %))
     (filter overlapping-assignments?)
     (count))
