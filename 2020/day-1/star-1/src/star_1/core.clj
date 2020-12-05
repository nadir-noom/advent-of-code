(ns star-1.core
  (:gen-class))

(defn expense-report [input]
  (set (for [input-a input
             input-b input
             :when (= 2020 (+ input-a input-b))]
        (* input-a input-b))))

(defn -main [input]
  (->> input
       (map #(Integer/parseInt %))
       (expense-report)
       (println)))
