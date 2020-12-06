(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-parse-password
  (testing "Should return a password-rule tuple parsed from the given rule-string"
    (is (= ["password" {:first 1 :second 3 :pattern \p}] (parse-password "1-3 p: password")))
    (is (= ["password" {:first 4 :second 12 :pattern \a}] (parse-password "4-12 a: password")))))

(deftest test-valid-password?
  (testing "Should return false when the supplied password violates the supplied rule"
    (is (false? (valid-password? "password" {:first 1 :second 6 :pattern \z})))
    (is (false? (valid-password? "password" {:first 3 :second 4 :pattern \s}))))
  (testing "Should return true when the supplied password matches the supplied rule"
    (is (true? (valid-password? "password" {:first 1 :second 6 :pattern \p})))))
