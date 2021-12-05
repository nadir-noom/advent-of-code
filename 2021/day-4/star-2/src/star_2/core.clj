(ns star-2.core
  (:require [clojure.string :as str]
            [clojure.walk :refer [prewalk]]
            [clojure.core.matrix :as matrix])
  (:gen-class))

(defn update-board [board value]
  (prewalk #(when (not= value %) %) board))

(defn bingo? [board]
  (or (some true? (map #(every? nil? %) (matrix/rows board)))
      (some true? (map #(every? nil? %) (matrix/columns board)))))

(defn losing-board [seq starting-boards]
  (loop [[number & rest] seq
          boards starting-boards
          last-winning-board {}]
    (if number
      (let [updated-boards (map #(update-board % number) boards)
            winning-board (first (filter bingo? updated-boards))]
        (if winning-board
          (recur rest (remove bingo? updated-boards) {:board winning-board :number number})
          (recur rest updated-boards last-winning-board)))
      (* (:number last-winning-board)
         (apply + (filter some? (flatten (:board last-winning-board))))))))

(defn create-boards [input]
  (->> input
       (drop 1)
       (remove str/blank?)
       (map str/trim)
       (map #(str/split % #"\s+"))
       (prewalk #(if (string? %) (Integer/parseInt %) %))
       (partition 5)))

(defn parse-input [input]
  {:seq (map #(Integer/parseInt %) (str/split (first input) #","))
   :boards (create-boards input)})

(defn -main [input]
  (let [{seq :seq starting-boards :boards} (parse-input input)]
    (println "The losing score is:" (losing-board seq starting-boards))))
