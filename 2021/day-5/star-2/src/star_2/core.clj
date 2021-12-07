(ns star-2.core
  (:require [clojure.string :as str]
            [clojure.walk :refer [prewalk]]
            [clojure.core.matrix :as matrix])
  (:gen-class))

(defn plot [{start :start end :end :as coordinate}]
  (let [x-coords (if (< (first start) (first end))
                   (range (first start) (inc (first end)))
                   (reverse (range (first end) (inc (first start)))))
        y-coords (if (< (second start) (second end))
                   (range (second start) (inc (second end)))
                   (reverse (range (second end) (inc (second start)))))]
    (cond
      (= (count x-coords) 1)
        (assoc coordinate :path (partition 2 (interleave (repeat (first x-coords)) y-coords)))
      (= (count y-coords) 1)
        (assoc coordinate :path (partition 2 (interleave x-coords (repeat (first y-coords)))))
      :else
        (assoc coordinate :path (partition 2 (interleave x-coords y-coords))))))

(defn vertex-frequencies [coordinates]
  (->> coordinates
       (map plot)
       (mapcat :path)
       (frequencies)
       (map val)
       (filter #(> % 1))
       (count)))

(defn parse-input [input]
  (->> input
       (map #(str/split % #"\s->\s+"))
       (map #(hash-map :start (str/split (first %) #",")
                       :end (str/split (second %) #",")))
       (prewalk #(if (string? %) (Integer/parseInt %) %))))

(defn -main [input]
  (->> input
       (parse-input)
       (vertex-frequencies)
       (println)))
