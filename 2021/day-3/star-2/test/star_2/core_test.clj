(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-bin->dec
  (testing "Should return the binary value of the supplied 12-bit binary number"
    (is (= 0 (bin->dec "000000000000")))
    (is (= 1 (bin->dec "000000000001")))
    (is (= 2 (bin->dec "000000000010")))
    (is (= 9 (bin->dec "000000001001")))
    (is (= 2049 (bin->dec "100000000001")))
    (is (= 9 (bin->dec "1001")))))

(deftest test-filter-input
  (testing "Should return a number that matches the supplied oxygen-matcher rule (most common bit, biased towards 1)"
    (is (= 10 (filter-input ["1000" "1100" "1010"] oxygen-matcher)))
  (testing "Should return a number that matches the supplied co2-matcher rule (least common bit, biased towards 0)"
    (is (= 10 (filter-input ["0000" "0100" "1010"] co2-matcher))))))
