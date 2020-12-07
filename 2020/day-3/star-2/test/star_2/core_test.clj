(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-next-position
  (testing "Should return a data structure representing the next position moved to given a row"
    (is (= [{:position 0 :symbol \#}]
           (next-position [] 3 "#...##")))
    (is (= [{:position 1} {:position 4 :symbol \#}]
           (next-position [{:position 1}] 3 "#...##")))
    (is (= [{:position 4} {:position 1 :symbol \.}]
           (next-position [{:position 4}] 3 "#...##")))
    (is (= [{:position 1} {:position 4} {:position 1 :symbol \.}]
           (next-position [{:position 1} {:position 4}] 3 "#...##")))))

(deftest test-tree-count
  (testing "Should return the number of trees encountered on a given vector"
    (let [input [".##..##."
                 "..##..##"
                 ".#.###.#"]]
      (is (= 2 (tree-count input 2 1)))
      (is (= 1 (tree-count input 3 2))))))
