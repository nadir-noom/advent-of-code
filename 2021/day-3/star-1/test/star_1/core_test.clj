(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-bin->dec
  (testing "Should return the binary value of the supplied 12-bit binary number"
    (is (= 0 (bin->dec "000000000000")))
    (is (= 1 (bin->dec "000000000001")))
    (is (= 2 (bin->dec "000000000010")))
    (is (= 9 (bin->dec "000000001001")))
    (is (= 2049 (bin->dec "100000000001")))
    (is (= 9 (bin->dec "1001")))))

(deftest test-calculate-ge
  (testing "Should return the calculation of gamma * epsilon"
    (testing "110 100 000 -> 100, 011 -> 4 * 3 -> 12"
      (is (= 12 (calculate-ge [{\0 1 \1 2} {\0 2 \1 1} {\0 3 \1 0}]))))
    (testing "110 101 001 -> 101, 010 -> 5 * 2 -> 10"
      (is (= 10 (calculate-ge [{\0 1 \1 2} {\0 2 \1 1} {\0 1 \1 2}]))))))
