#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn surrounding-coords[x y]
   (let [distance 3
         directions [[0 -1][0 1][-1 0][1 0][-1 -1][1 -1][-1 1][1 1]]]
    (for [d directions]
      (map (fn [i]
             [(+ x (* i (first d)))
              (+ y (* i (second d)))])
           (range 1 (inc distance))))))

(defn find-xmas[word-search]
  (for [y (range (count word-search))
        x (range (count (first word-search)))
        :when (= (get-in word-search [y x]) \X)
        :let [surrounding (surrounding-coords x y)]]
    (for [direction surrounding
          :let [coords (map (fn [[x y]]
                              (get-in word-search [y x])) direction)]
          :when (= coords [\M \A \S])]
      coords)))

 (->> (slurp "data.txt")
      (str/split-lines)
      (mapv #(vec (seq (char-array %))))
      (find-xmas)
      (filter seq)
      (mapcat identity)
      (count)
      (println))
