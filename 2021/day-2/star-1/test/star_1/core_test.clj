(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-calculate-course
  (testing "Should return the result of depth * forward movement"
    (is (= 0 (calculate-course 0 0 [])))
    (is (= 1 (calculate-course 1 1 [])))
    (is (= 0 (calculate-course 0 0 [["forward" 0] ["up" 0] ["down" 0]])))
    (is (= 4 (calculate-course 0 0 [["forward" 2] ["up" 1] ["down" 3]])))))
