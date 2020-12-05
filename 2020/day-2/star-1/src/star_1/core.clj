(ns star-1.core
  (:require [clojure.core.strint :refer [<<]]
            [clojure.string :as string])
  (:gen-class))

(defn parse-password [password-line]
  (let [[_ min max pattern password] (first (re-seq #"(\d+)-(\d+) (\S+): (\w+)" password-line))]
    [password {:min (Integer/parseInt min)
               :max (Integer/parseInt max)
               :pattern pattern}]))

(defn valid-password? [password {min :min max :max pattern :pattern}]
  (let [pattern (<< "^([^~{pattern}]*~{pattern}){~{min},~{max}}[^~{pattern}]*$")]
    (some? (re-matches (re-pattern pattern) password))))

(defn -main[input]
  (->> input
       (map parse-password)
       (filter #(apply valid-password? %) )
       (count)
       (println)))
