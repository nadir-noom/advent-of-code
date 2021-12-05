(ns star-1.core
  (:require [clojure.string :as str]
            [clojure.walk :refer [prewalk]]
            [clojure.core.matrix :as matrix])
  (:gen-class))

(defn plot [{start :start end :end :as coordinate}]
  (let [x-diff (Math/abs (- (first start) (first end)))
        y-diff (Math/abs (- (second start) (second end)))]
    (if (> x-diff y-diff)
      (assoc coordinate :path (map #(vector % (second start)) (range (min (first start) (first end)) (inc (max (first start) (first end))))))
      (assoc coordinate :path (map #(vector (first start) %) (range (min (second start) (second end)) (inc (max (second start) (second end)))))))))

(defn same-x-or-y? [{start :start end :end}]
  (or (= (first start) (first end))
      (= (second start) (second end))))

(defn parse-input [input]
  (->> input
       (map #(str/split % #"\s->\s+"))
       (map #(hash-map :start (str/split (first %) #",") :end (str/split (second %) #",")))
       (prewalk #(if (string? %) (Integer/parseInt %) %))))

(defn -main [input]
  (->> input
       (parse-input)
       (filter same-x-or-y?)
       (map plot)
       (mapcat :path)
       (frequencies)
       (map val)
       (filter #(> % 1))
       (count)
       (println)))
