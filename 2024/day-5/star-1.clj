#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn rules [input]
  (let [split-input (map #(str/split % #"\|") (str/split-lines input))]
    (reduce (fn [rule-set [x y]]
              (update rule-set x #(set (conj % y))))
             {}
             split-input)))

(defn pages [input]
  (->> input
       (str/split-lines)
       (map #(str/split % #","))))

(defn valid? [input rules]
  (for [x input
        :let [before (take-while #(not= % x) input)
              after (drop 1 (drop-while #(not= % x) input))]]
    (not-any? #(contains? (get rules x) %) before)))

(defn validate [[rule-input page-input]]
  (for [page (pages page-input)
        :let [validation-result (valid? page (rules rule-input))]
        :when (every? true? validation-result)]
    page))

(->> (slurp "data.txt")
     (#(str/split % #"\s\n"))
     (validate)
     (map #(nth % (quot (count %) 2)))
     (map #(Integer/parseInt %))
     (apply +)
     (println))
