(ns star-2.core
  (:require [clojure.string :as str]
            [clojure.walk :refer [prewalk]]
            [clojure.core.matrix :as matrix])
  (:gen-class))

(defn breed-lantern-fish [fish]
  (let [new-fish (second (first fish))
        updated-fish (mapv #(vector (dec (first %)) (second %)) (drop 1 fish))
        fish-with-renewed (update updated-fish 6 #(vector 6 (+ (second %) new-fish)))]
    (concat fish-with-renewed [[8 new-fish]])))

(defn breed-fish-for-days [days fish]
  (loop [current-fish fish
         current-day days]
    (if (> current-day 0)
      (recur (breed-lantern-fish current-fish) (dec current-day))
      (apply + (map second current-fish)))))

(defn parse-input [input]
  (let [occurance (->> input
                       (first)
                       (#(str/split % #","))
                       (map #(Integer/parseInt %))
                       (frequencies))]
    (for [x (range 0 9)]
      [x (get occurance x 0)])))

(defn -main [input]
  (->> input
       (parse-input)
       (breed-fish-for-days 256)
       (println)))
