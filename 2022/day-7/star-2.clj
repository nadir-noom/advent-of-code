#!/usr/bin/env bb
(require '[clojure.set :as set])
(require '[clojure.walk :as w])

(defn clean-up [node]
  (if (vector? node)
    (if (map? (second node))
      (when (>= (:total (second node)) 1272621)
        (println (:total (second node)) (first node)))))
  node)

(defn df [node]
  (if (vector? node)
    (if (map? (second node))
      (let [dir (second node)
            size (apply + (filter #(not (map? %)) (vals dir)))
            child-size (apply + (map :total (filter map? (vals dir))))]
       [(first node) (assoc-in dir [:total] (+ child-size size))])
      node)
    node))

(defn fs-tree [commands]
  (loop [commands commands
         fs {}
         path []]
    (let [command (str/split (first commands) #" ")]
      (cond
        (and (= (nth command 0) "$") (= (nth command 1) "cd"))
          (if (= (nth command 2) "..")
            (recur (drop 1 commands) fs (vec (drop-last path)))
            (recur (drop 1 commands) fs (conj path (nth command 2))))
        (or (= (nth command 0) "dir") (and (= (nth command 0) "$") (= (nth command 1) "ls")))
          (recur (drop 1 commands) fs path)
        :default
          (let [updated-fs (assoc-in fs (conj path (nth command 1)) (Integer/parseInt (nth command 0)))]
            (if (> (count commands) 1)
              (recur (drop 1 commands) updated-fs path)
              updated-fs))))))

(->> (slurp "data.txt")
     (str/split-lines)
     (fs-tree)
     (w/postwalk df)
     (w/postwalk clean-up))
