(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-sonar-sweep
  (testing "Should return the number of pairs (offset by 1) where the second number is larger"
    (is (= 0 (sonar-sweep nil)))
    (is (= 0 (sonar-sweep [])))
    (is (= 0 (sonar-sweep [2])))
    (is (= 3 (sonar-sweep [2 3 4 5])))
    (is (= 2 (sonar-sweep [2 1 4 5])))))
