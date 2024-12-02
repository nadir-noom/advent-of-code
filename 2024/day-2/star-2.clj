#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn safe-increase? [list]
  (every? #(>= 3 % 1) list))

(defn ordered? [list]
  (or (apply < list)
      (apply > list)))

(defn safe-report? [row]
  (let [parsed-row (map #(Integer/parseInt %) row)
        differences (map #(abs (- (first %) (second %))) (partition 2 1 parsed-row))]
    (and (ordered? parsed-row)
         (safe-increase? differences))))

(defn without-nth [coll n]
  (concat (take n coll) (drop (inc n) coll)))

(defn try-safe-report? [original-row new-row nth]
  (or (safe-report? new-row)
      (and (< nth (count original-row))
           (recur original-row (without-nth original-row nth) (inc nth)))))

(defn parse-line [line]
  (str/split line #"\s+"))

(->> (slurp "data.txt")
     (str/split-lines)
     (map parse-line)
     (filter #(try-safe-report? % % 0))
     (#(print (count %))))
