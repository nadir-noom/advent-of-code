#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn diagonal-coords[x y]
  [[[(dec x) (dec y)] [(inc x) (inc y)]]
    [[(dec x) (inc y)] [(inc x) (dec y)]]])

(defn find-x-of-mas[surrounding word-search]
  (for [direction surrounding
        :let [coords (map (fn [[x y]]
                            (get-in word-search [y x])) direction)]
        :when (= (set coords) #{\M \S})]
    true))

(defn find-xmas[word-search]
  (for [y (range (count word-search))
        x (range (count (first word-search)))
        :when (= (get-in word-search [y x]) \A)]
    (let [diagonal-count (count (find-x-of-mas (diagonal-coords x y) word-search))]
      (= 2 diagonal-count))))

 (->> (slurp "data.txt")
      (str/split-lines)
      (mapv #(vec (seq (char-array %))))
      (find-xmas)
      (filter true?)
      (count)
      (println))
