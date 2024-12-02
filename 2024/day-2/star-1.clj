#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn safe-increase? [list]
  (every? #(>= 3 % 1) list))

(defn ordered? [list]
  (or (apply < list)
      (apply > list)))

(defn safe-report [row]
  (let [parsed-row (map #(Integer/parseInt %) row)
        differences (map #(abs (- (first %) (second %))) (partition 2 1 parsed-row))]
    (and (ordered? parsed-row)
         (safe-increase? differences))))

(defn parse-line [line]
  (str/split line #"\s+"))

(->> (slurp "data.txt")
     (str/split-lines)
     (map parse-line)
     (filter safe-report)
     (#(print (count %))))
