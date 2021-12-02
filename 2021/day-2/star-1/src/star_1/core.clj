(ns star-1.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn calculate-course [startX startY course]
  (let [{up "up" down "down" forward "forward"} (group-by first course)]
    (* (+ (- startY (apply + (map second up))) (apply + (map second down)))
       (+ startX (apply + (map second forward))))))

(defn parse-line [line]
  (let [[direction value] (str/split line #" ")]
    [direction (Integer/parseInt value)]))

(defn -main [input]
  (->> input
       (map parse-line)
       (calculate-course 0 0)
       (println)))
