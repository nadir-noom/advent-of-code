#!/usr/bin/env bb
(require '[clojure.set :as set])

(def stacks [["D" "T" "R" "B" "J" "L" "W" "G"]
             ["S" "W" "C"]
             ["R" "Z" "T" "M"]
             ["D" "T" "C" "H" "S" "P" "V"]
             ["G" "P" "T" "L" "D" "Z"]
             ["F" "B" "R" "Z" "J" "Q" "C" "D"]
             ["S" "B" "D" "J" "M" "F" "T" "R"]
             ["L" "H" "R" "B" "T" "V" "M"]
             ["Q" "P" "D" "S" "V"]])

(defn move-stacks [stacks instruction]
  (let [[count from to] (map #(Integer/parseInt %) (re-seq #"\d+" instruction))
        to-move (reverse (take-last count (nth stacks (dec from))))]
    (-> stacks
        (update (dec to) #(vec (concat % to-move)))
        (update (dec from) #(vec (drop-last count %))))))

(->> (slurp "data.txt")
     (str/split-lines)
     (drop-while #(not (str/starts-with? % "move")))
     (reduce move-stacks stacks)
     (map last)
     (apply str))
