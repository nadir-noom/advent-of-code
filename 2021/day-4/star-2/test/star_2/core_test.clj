(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-update-board
  (testing "Should update the supplied bingo board with all occurances of the supplied value replaced with nil"
    (is (= nil (update-board nil 4)))
    (is (= [] (update-board [] 4)))
    (is (= [[1 2][3 nil]] (update-board [[1 2][3 4]] 4)))
    (is (= [[1 2][nil nil]] (update-board [[1 2][3 3]] 3)))))

(deftest test-bingo?
  (testing "Should return true only when an entire row/column is nil"
    (is (not (bingo? [[nil 1][2 nil]])))
    (is (bingo? [[nil nil][2 2]]))
    (is (bingo? [[nil 1][nil 2]]))))

(deftest test-losing-board
  (testing "Should return the score of the losing board (last number * (sum remaining numbers))"
    (let [input [[[1 10][11 4]] [[1 3][4 2]]]]
      (is (= 20 (losing-board [1 10 12 4] input))))))
