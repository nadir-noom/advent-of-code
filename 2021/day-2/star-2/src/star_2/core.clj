(ns star-2.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn calculate-course [course]
  (loop [[[direction value] & remaining] course
         horizontal 0
         depth 0
         aim 0]
    (case direction
      "forward" (recur remaining (+ horizontal value) (+ depth (* value aim)) aim)
      "up" (recur remaining horizontal depth (- aim value))
      "down" (recur remaining horizontal depth (+ aim value))
      (* horizontal depth))))

(defn parse-line [line]
  (let [[direction value] (str/split line #" ")]
    [direction (Integer/parseInt value)]))

(defn -main [input]
  (->> input
       (map parse-line)
       (calculate-course)
       (println)))
