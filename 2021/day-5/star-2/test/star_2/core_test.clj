(ns star-2.core-test
  (:require [clojure.test :refer :all]
            [star-2.core :refer :all]))

(deftest test-plot
  (testing "Should plot a point between two coordinates and produce a list of coordinates between them"
    (let [input {:start [1 2] :end [4 2]}]
      (is (= [[1 2] [2 2] [3 2] [4 2]] (:path (plot input)))))
    (let [input {:start [4 2] :end [1 2]}]
      (is (= [[4 2] [3 2] [2 2] [1 2]] (:path (plot input)))))
    (let [input {:start [2 2] :end [2 4]}]
      (is (= [[2 2] [2 3] [2 4]] (:path (plot input)))))
    (let [input {:start [2 4] :end [2 2]}]
      (is (= [[2 4] [2 3] [2 2]] (:path (plot input)))))
    (let [input {:start [1 1] :end [3 3]}]
      (is (= [[1 1] [2 2] [3 3]] (:path (plot input)))))
    (let [input {:start [3 3] :end [1 1]}]
      (is (= [[3 3] [2 2] [1 1]] (:path (plot input)))))
    (let [input {:start [3 1] :end [1 3]}]
      (is (= [[3 1] [2 2] [1 3]] (:path (plot input)))))
    (let [input {:start [1 3] :end [3 1]}]
      (is (= [[1 3] [2 2] [3 1]] (:path (plot input)))))))

(deftest test-vertex-frequencies
  (let [coordinates [{:start [0 9] :end [5 9]}
                     {:start [8 0] :end [0 8]}
                     {:start [9 4] :end [3 4]}
                     {:start [2 2] :end [2 1]}
                     {:start [7 0] :end [7 4]}
                     {:start [6 4] :end [2 0]}
                     {:start [0 9] :end [2 9]}
                     {:start [3 4] :end [1 4]}
                     {:start [0 0] :end [8 8]}
                     {:start [5 5] :end [8 2]}]]
    (is (= 12 (vertex-frequencies coordinates)))))
