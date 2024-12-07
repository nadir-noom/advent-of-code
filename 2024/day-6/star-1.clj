#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn rotate-270 [m]
  (loop [repeated 3
         m m]
    (if (zero? repeated)
      (mapv vec m)
      (recur (dec repeated) (mapv rseq (apply mapv vector m))))))

(defn find-guard[input]
  (first (for [y (range (count input))
               x (range (count (first input)))
               :when (= \^ (get-in input [y x]))]
           [y x])))

(defn new-guard [[y x]]
  (when (> y 0)
    [(dec y) x]))

(defn plot-route [input]
  (let [guard (find-guard input)
        new-guard (new-guard guard)]
    (if (and guard new-guard)
      (if (not= \# (get-in input new-guard))
        (-> input
            (assoc-in guard \X)
            (assoc-in new-guard \^)
            (recur))
        (recur (rotate-270 input)))
      (assoc-in input guard \X))))

(->> (slurp "data.txt")
     (str/split-lines)
     (mapv #(vec (seq (char-array %))))
     (plot-route)
     (flatten)
     (filter #(= % \X))
     (count)
     (println))
