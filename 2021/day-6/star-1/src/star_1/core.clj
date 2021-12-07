(ns star-1.core
  (:require [clojure.string :as str]
            [clojure.walk :refer [prewalk]]
            [clojure.core.matrix :as matrix])
  (:gen-class))

(defn breed-lantern-fish [fish]
  (let [lantern-fish (map dec fish)
        new-fish (repeat (count (filter #(= % -1) lantern-fish)) 8)]
    (concat (map #(if (= % -1) 6 %) lantern-fish) new-fish)))

(defn breed-fish-for-days [days fish]
  (loop [current-fish fish
         current-day days]
    (if (> current-day 0)
      (recur (breed-lantern-fish current-fish) (dec current-day))
      (count current-fish))))

(defn parse-input [input]
  (->> input
       (first)
       (#(str/split % #","))
       (map #(Integer/parseInt %))))

(defn -main [input]
  (->> input
       (parse-input)
       (breed-fish-for-days 256)
       (println)))
