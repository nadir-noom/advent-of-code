#!/usr/bin/env bb
(defn parse-line [trees]
  (->> trees
       (#(str/split % #""))
       (map #(Integer/parseInt %))
       (mapv #(hash-map :height %))))

(defn transpose [m]
  (apply mapv vector m))

(defn visible? [others {height :height}]
  (or (= (count others) 0))
      (every? #(> height %) (map :height others)))

(defn make-visible [row]
  (reduce
      #(if (visible? %1 %2)
        (conj %1 (assoc %2 :visible true))
        (conj %1 %2))
      []
      row))

(defn visible-trees-in-row [row]
  (-> row
      (make-visible)
      (reverse)
      (make-visible)
      (reverse)))

(->> (slurp "data.txt")
     (str/split-lines)
     (map parse-line)
     (map visible-trees-in-row)
     (transpose)
     (mapcat visible-trees-in-row)
     (filter :visible)
     (count))
