(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-breed-lantern-fish
  (testing "Should decrement any non-zero fish counters by 1"
    (is (= [[0 0] [1 1] [2 1] [3 1] [4 0] [5 0] [6 0] [7 0] [8 0]]
           (breed-lantern-fish [[0 0] [1 0] [2 1] [3 1] [4 1] [5 0] [6 0] [7 0] [8 0]]))))
  (testing "Should create new fish at position 8 and renew fish."
    (is (= [[0 1] [1 1] [2 1] [3 0] [4 0] [5 0] [6 1] [7 0] [8 1]]
           (breed-lantern-fish [[0 1] [1 1] [2 1] [3 1] [4 0] [5 0] [6 0] [7 0] [8 0]])))))

(deftest test-breed-fish-for-days
  (testing "Should breed fish for given days"
    (is (= 6 (breed-fish-for-days 2 [[0 0] [1 1] [2 1] [3 2] [4 1] [5 0] [6 0] [7 0] [8 0]])))))
