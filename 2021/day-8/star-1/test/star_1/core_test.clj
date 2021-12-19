(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-translate-segments
  (testing "Should return a translation for all segments in provided input"
    (is (= 1 (get (translate-segments ["fg"]) 1)))
    (is (= 2 (get (translate-segments ["fg" "gf"]) 1)))
    (is (= 1 (get (translate-segments ["abcd"]) 4)))
    (is (= 1 (get (translate-segments ["abc"]) 7)))
    (is (= 1 (get (translate-segments ["abcdefg"]) 8)))))
