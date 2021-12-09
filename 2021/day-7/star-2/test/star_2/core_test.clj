(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-cost-to-x
  (testing "Should calculate the cost of moving crabs to the supplied point (x)"
    (is (= 206 (cost-to-x [16 1 2 0 4 2 7 1 2 14] 2)))
    (is (= 168 (cost-to-x [16 1 2 0 4 2 7 1 2 14] 5)))))

(deftest test-align-crabs
  (testing "Should output the lowest cost possible to align all crabs to the same position"
    (is (= 168 (align-crabs [16 1 2 0 4 2 7 1 2 14])))))
