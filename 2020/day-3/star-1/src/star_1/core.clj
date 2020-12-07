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

(defn tree-count [input row-speed column-speed]
  (->> input
       (take-nth column-speed)
       (reduce #(next-position %1 row-speed %2) [])
       (filter #(= \# (:symbol %)))
       (count)))

(defn -main[input]
  (-> input
      (tree-count 3 1)
      (println)))
