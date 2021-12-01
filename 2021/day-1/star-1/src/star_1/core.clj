(ns star-1.core
  (:gen-class))

(defn sonar-sweep [numbers]
  (->> numbers
       (partition 2 1)
       (map #(> (second %) (first %)))
       (filter true?)
       (count)))

(defn -main [input]
  (->> input
       (map #(Integer/parseInt %))
       (sonar-sweep)
       (println)))
