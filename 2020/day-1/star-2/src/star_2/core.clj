(ns star-2.core
  (:gen-class))

(defn expense-report [input]
  (set (for [input-a input
             input-b input
             input-c input
             :when (= 2020 (+ input-a input-b input-c))]
        (* input-a input-b input-c))))

(defn -main [input]
  (->> input
       (map #(Integer/parseInt %))
       (expense-report)
       (println)))
