(ns star-1.core
  (:require [clojure.core.strint :refer [<<]]
            [clojure.string :as string])
  (:gen-class))

(defn next-position [positions speed row]
  (let [current-position (get (last positions) :position)
        new-position (if current-position
                        (+ current-position speed)
                        0)]
    (if (>= new-position (count row))
      (conj positions {:position (- new-position (count row))
                       :symbol (nth row (- new-position (count row)))})
      (conj positions {:position new-position
                       :symbol (nth row new-position)}))))

(defn -main[input]
  (->> input
       (reduce #(next-position %1 3 %2) [])
       (filter #(= \# (:symbol %)))
       (count)
       (println)))
