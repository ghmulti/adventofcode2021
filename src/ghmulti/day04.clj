(ns ghmulti.day04
  (:require
   [clojure.set]
   [ghmulti.adventofcode2021 :as main]))

(defn- parser [rows]
  (map #(-> (clojure.string/trim %) (clojure.string/split #" +")) rows))

(defn- find-match-row [numbers row]
  ;; (println "Searching numbers " numbers " in row " row)
  (filter #(let [
                 block (set %)
                 matches (clojure.set/intersection block numbers)
                 ;; _ (if (> (count matches) 0) (println "Got matches" (count matches) matches % " for numbers " numbers))
                ]
            (= (count block) (count matches))) row))

(defn- find-match [rows numbers]
  ;; (println "Searching numbers " numbers)
  (let [
        t1 (map #(find-match-row numbers %) rows)        
        to-columns-mapper (fn [row] (->> (apply interleave row) (partition 5)))
        columns  (map to-columns-mapper rows)        
        t2 (map (partial find-match-row numbers) columns)
       ]
    (if (first (concat (flatten t1) (flatten t2))) {:rows rows :numbers numbers} nil))
  )
   

(defmethod main/advent 4 [_]
  (let [
        [str_numbers _ & boards] (clojure.string/split-lines (slurp "resources/day04.txt"))
        numbers (clojure.string/split str_numbers #",")
        ;; _ (println numbers)
        numbers-iterator (->> (count numbers) inc range (map #(take % numbers)) (drop 5))
        ;; _ (prn (take 3 numbers-iterator))
        ;; _ (prn (take-last 3 numbers-iterator))
        ppboard (->> (partition 5 6 boards) (map parser))
        ;; _ (prn (take 2 ppboard))        
        ;; _ (prn (take-last 2 ppboard))
        set-numbers (map set numbers-iterator)
        res (map (partial find-match ppboard) set-numbers)
        matching-enty (first res)
        _ (println "Matching entry:" matching-enty)
       ]                
    (println "Part 1:" " Part 2:")))