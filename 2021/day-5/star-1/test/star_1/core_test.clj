(ns star-1.core-test
  (:require [clojure.test :refer :all]
            [star-1.core :refer :all]))

(deftest test-plot
  (testing "Should plot a point between two coordinates and produce a list of coordinates between them"
    (let [input {:start [1 2] :end [4 2]}]
      (is (= [[1 2] [2 2] [3 2] [4 2]] (:path (plot input)))))
    (let [input {:start [4 2] :end [1 2]}]
      (is (= [[1 2] [2 2] [3 2] [4 2]] (:path (plot input)))))
    (let [input {:start [2 2] :end [2 4]}]
      (is (= [[2 2] [2 3] [2 4]] (:path (plot input)))))
    (let [input {:start [2 4] :end [2 2]}]
      (is (= [[2 2] [2 3] [2 4]] (:path (plot input)))))))
