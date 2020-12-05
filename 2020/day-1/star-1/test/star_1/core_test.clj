(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-expense-report
  (testing "Should return a set of values that are multiplications of value pairs that add to 2020"
    (is (= #{40000} (expense-report [2000 20])))
    (is (= #{40000 192000} (expense-report [100 1920 890 40 2000 20])))))
