(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-calculate-course
  (testing "Should return the result of depth * forward movement adjusted for 'aim'"
    (is (= 0 (calculate-course [])))
    (is (= 0 (calculate-course [["forward" 0] ["up" 0] ["down" 0]])))
    (is (= 900 (calculate-course [["forward" 5] ["down" 5] ["forward" 8] ["up" 3] ["down" 8] ["forward" 2]])))))
