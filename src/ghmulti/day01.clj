(ns ghmulti.day01
  (:require
   [ghmulti.adventofcode2021 :as main]))

(defmethod main/advent 1 [_]
  (let [lines (map #(Integer/parseInt %) (clojure.string/split-lines (slurp "resources/day01.txt")))
        ;; _ (println (take 5 lines))
        ;; part 1
        pairs (partition 2 1 lines)
        pairs_mapping (map (fn [[a b]] (if (< a b) 1 0)) pairs)
        ;; part 2
        sliding_pairs (partition 4 1 lines)      
        sliding_pairs_mapping (map (fn [[a b c d]] (if (< (apply + [a b c]) (apply + [b c d])) 1 0)) sliding_pairs)]
    (println "Part 1:" (apply + pairs_mapping) " Part 2:" (apply + sliding_pairs_mapping)))
    ;; Part 1: 1791  Part 2: 1822
  )