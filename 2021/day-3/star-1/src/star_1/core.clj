(ns star-1.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn bin->dec [input]
  (let [bits (map #(Integer/parseInt (apply str %)) (partition 1 input))
        pow (reverse (take (count input) (iterate #(* 2 %) 1)))]
    (apply + (map #(apply * %) (partition 2 (interleave bits pow))))))

(defn calculate-ge [frequencies]
  (let [gamma (reduce #(conj %1 (if (> (get %2 \0) (get %2 \1)) 0 1)) [] frequencies)
        epsilon (reduce #(conj %1 (if (< (get %2 \0) (get %2 \1)) 0 1)) [] frequencies)]
    (* (bin->dec (str/join "" gamma))
       (bin->dec (str/join "" epsilon)))))

(defn bit-frequencies [input]
   (for [x (partition (count input) (apply interleave input))]
     (frequencies x)))

(defn -main [input]
  (->> input
       (bit-frequencies)
       (calculate-ge)
       (println)))
