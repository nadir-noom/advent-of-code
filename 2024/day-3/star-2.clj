#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn do? [command]
  (re-find #"do\(\)" command))

(defn don't? [command]
  (re-find #"don't\(\)" command))

(defn enabled-commands [final-commands all-commands]
  (let [commands-to-add (remove do? (take-while #(not (don't? %)) all-commands))
        remaining-commands (drop-while #(not (do? %)) (drop-while #(not (don't? %)) all-commands))]
    (if (seq remaining-commands)
      (recur (concat final-commands commands-to-add) remaining-commands)
      (concat final-commands commands-to-add))))

(defn mul[command]
  (apply * (map #(Integer/parseInt %) (re-seq #"\d+" command))))

(defn extract-commands [line]
  (re-seq #"mul\([0-9]{1,3},[0-9]{1,3}\)|do\(\)|don't\(\)" line))

(->> (slurp "data.txt")
     (str/split-lines)
     (mapcat extract-commands)
     (enabled-commands [])
     (map mul)
     (apply +)
     (println))
