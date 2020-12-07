(ns star-2.core
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

(defn tree-count [input row-speed column-speed]
  (->> input
       (take-nth column-speed)
       (reduce #(next-position %1 row-speed %2) [])
       (filter #(= \# (:symbol %)))
       (count)))

(defn -main[input]
  (println (apply * (map #(apply tree-count input %)
                         [[1 1] [3 1] [5 1] [7 1] [1 2]]))))
