(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-aggregate-3
  (testing "Should aggregate the last 3 numbers together and produce a list with the results"
    (is (= [] (aggregate-3 nil)))
    (is (= [] (aggregate-3 [])))
    (is (= [6] (aggregate-3 [6])))
    (is (= [6 5 3] (aggregate-3 [1 2 3])))
    (is (= [9 12 15 11 6] (aggregate-3 [2 3 4 5 6])))))

(deftest test-sonar-sweep
  (testing "Should return the number of pairs (offset by 1) where the second number is larger"
    (is (= 0 (sonar-sweep nil)))
    (is (= 0 (sonar-sweep [])))
    (is (= 0 (sonar-sweep [2])))
    (is (= 3 (sonar-sweep [2 3 4 5])))
    (is (= 2 (sonar-sweep [2 1 4 5])))))
