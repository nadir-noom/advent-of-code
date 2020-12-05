(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-parse-password
  (testing "Should return a password-rule tuple parsed from the given rule-string"
    (is (= ["password" {:min 1 :max 3 :pattern "p"}] (parse-password "1-3 p: password")))
    (is (= ["password" {:min 4 :max 12 :pattern "abc"}] (parse-password "4-12 abc: password")))))

(deftest test-valid-password?
  (testing "Should return false when the supplied password violates the supplied rule"
    (let [rule {:pattern "a"
                :max 2
                :min 1}]
      (is (false? (valid-password? "bcd" rule)))
      (is (false? (valid-password? "" rule)))))
  (testing "Should return true when the supplied password matches the supplied rule"
    (let [rule {:pattern "a"
                :max 3
                :min 1}]
      (is (true? (valid-password? "abcd" rule)))
      (is (true? (valid-password? "abcaad" rule))))))
