(ns star-2.core
  (:gen-class))

(defn aggregate-3 [numbers]
  (->> numbers
       (partition-all 3 1)
       (map #(apply + %))))

(defn sonar-sweep [numbers]
  (->> numbers
       (partition 2 1)
       (map #(> (second %) (first %)))
       (filter true?)
       (count)))

(defn -main [input]
  (->> input
       (map #(Integer/parseInt %))
       (aggregate-3)
       (sonar-sweep)
       (println)))
