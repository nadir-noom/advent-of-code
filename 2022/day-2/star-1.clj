#!/usr/bin/env bb
(require '[clojure.string :as str])

(def your-symbol->score {"X" 1    ;ROCK
                         "Y" 2    ;PAPER
                         "Z" 3})  ;SCISSORS

(defn score-round [[them you]]
  (let [symbol-score (get your-symbol->score you)]
    (case (str them you)
      ("CX" "BZ" "AY")
        (+ symbol-score 6)
      ("CY" "BX" "AZ")
        symbol-score
      (+ symbol-score 3))))

(->> (slurp "data.txt")
     (str/split-lines)
     (map #(str/split % #" "))
     (map score-round)
     (apply +))
