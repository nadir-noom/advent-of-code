(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-breed-lantern-fish
  (testing "Should decrement any non-zero fish counters by 1"
    (is (= [1 2 3] (breed-lantern-fish [2 3 4]))))
  (testing "Should set any fish counters of a value of 0 to 6"
    (is (= 6 (first (breed-lantern-fish [0 3 4])))))
  (testing "Should breed new fish with a value of 8 when a fish counter is reset"
    (is (= [6 8] (breed-lantern-fish [0])))
    (is (= [0 1 0 5 6 7 8] (breed-lantern-fish [1 2 1 6 0 8])))))

(deftest test-breed-fish-for-days
  (testing "Should breed fish for given days"
    (is (= 6 (breed-fish-for-days 2 [3 4 3 1 2])))))
