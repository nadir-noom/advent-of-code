(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-cost-to-x
  (testing "Should calculate the cost of moving crabs to the supplied point (x)"
    (is (= 37 (cost-to-x [16 1 2 0 4 2 7 1 2 14] 2)))))

(deftest test-align-crabs
  (testing "Should output the lowest cost possible to align all crabs to the same position"
    (is (= 37 (align-crabs [16 1 2 0 4 2 7 1 2 14])))))
