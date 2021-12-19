(ns star-1.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn translate-segments [display-number]
  (let [count-segments (fn [segments] (count (get (group-by count display-number) segments)))]
    {1 (count-segments 2)
     4 (count-segments 4)
     7 (count-segments 3)
     8 (count-segments 7)}))

(defn parse-input [input]
  (->> input
       (map #(str/split % #"\|"))
       (map #(hash-map :input (str/split (first %) #" ")
                       :output (str/split (second %) #" ")))))

(defn -main [input]
  (->> input
       (parse-input)
       (map :output)
       (map translate-segments)
       (mapcat vals)
       (apply +)
       (println)))
