(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

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
