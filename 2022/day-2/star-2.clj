#!/usr/bin/env bb
(require '[clojure.string :as str])

(def symbol->score {"A" 1    ;rock
                    "B" 2    ;paper
                    "C" 3})  ;scissors

(defn outcome->symbol [them outcome]
  (case outcome
    "X" (first (drop 1 (drop-while #(not= them %) ["A" "C" "B" "A"])))
    "Z" (first (drop 1 (drop-while #(not= them %) ["A" "B" "C" "A"])))
    "Y" them))

(defn score-round [[them outcome]]
  (let [symbol (outcome->symbol them outcome)
        score (symbol->score symbol)]
    (case outcome
      "X" score
      "Y" (+ score 3)
      (+ score 6))))

(->> (slurp "data.txt")
     (str/split-lines)
     (map #(str/split % #" "))
     (map score-round)
     (apply +))
