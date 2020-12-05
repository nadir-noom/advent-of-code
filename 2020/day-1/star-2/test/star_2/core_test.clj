(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-expense-report
  (testing "Should return a set of values that are multiplications of value triples that add to 2020"
    (is (= #{} (expense-report [2000 20])))
    (is (= #{38000} (expense-report [2000 19 1])))
    (is (= #{38000 190080} (expense-report [99 1920 890 40 2000 19 1])))))
