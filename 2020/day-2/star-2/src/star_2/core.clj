(ns star-2.core
  (:gen-class))

(defn parse-password [password-line]
  (let [[_ first second [pattern] password] (first (re-seq #"(\d+)-(\d+) (\w+): (\w+)" password-line))]
    [password {:first (Integer/parseInt first)
               :second (Integer/parseInt second)
               :pattern pattern}]))

(defn valid-password? [password {first :first second :second pattern :pattern}]
  (let [characters (set [(nth password (dec first))
                         (nth password (dec second))])]
    (and (contains? characters pattern)
         (= 2 (count characters)))))

(defn -main[input]
  (->> input
       (map parse-password)
       (filter #(apply valid-password? %) )
       (count)
       (println)))
