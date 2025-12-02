#!/usr/bin/env bb

(require '[clojure.string :as str])

(def directions {"L" - "R" +})

(defn position [current step]
  (let [direction (get directions (first step))
        new-position (direction current (second step))]
    (mod new-position 100)))

(defn turn-tumbler [tumbler step]
  (let [new-position (position (:position tumbler) step)]
    {:position new-position
     :password (if (= new-position 0) 
                 (inc (:password tumbler)) 
                 (:password tumbler))}))

(defn parse-line [line]
  (let [[direction & parts] (str/split line #"")]
    [direction (Integer/parseInt (apply str parts))]))

(->> (slurp "data.txt")
     (str/split-lines)
     (map parse-line)
     (reduce turn-tumbler {:position 50 :password 0})
     (println))
