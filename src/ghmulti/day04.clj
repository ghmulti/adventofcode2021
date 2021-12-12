(ns ghmulti.day04
  (:require
   [clojure.set]
   [ghmulti.adventofcode2021 :as main]))

(defn- parser [rows]
  (let [mapper (fn [x] (->> (clojure.string/split (clojure.string/trim x) #" +")
                            (map #(Integer/parseInt %))))]
    (map mapper rows)))

(defn- read-input []
  (let [[str_numbers _ & boards] (clojure.string/split-lines (slurp "resources/day04.txt"))
        numbers (map #(Integer/parseInt %) (clojure.string/split str_numbers #","))
        numbers-iterator (->> (count numbers) inc range (map #(take % numbers)) (drop 5))
        ppboard (->> (partition 5 6 boards) (map parser))]
    {:board ppboard :numbers-iterator numbers-iterator}))

(defn- find-match-in-block [numbers rows]
  ;; (println "Searching numbers in block" rows numbers)
  (let [number-set (set numbers)
        columns (->> (apply interleave rows) (partition 5))
        match-in-rows (some #(= 5 (count (clojure.set/intersection (set %) number-set))) rows)
        match-in-columns (some #(= 5 (count (clojure.set/intersection (set %) number-set))) columns)]
    (if (or match-in-rows match-in-columns) {:numbers numbers :block rows} nil)))

(defn- find-first-match-on-board [board numbers]
  ;; (println "Searching numbers on board" numbers)
  (some (partial find-match-in-block numbers) board))
  
;; FUUUUUU \_(x_x)_/
(defn- find-last-match-on-board [board numbers]
  ;; (println "Searching numbers on board" numbers)
  (if (every? (partial find-match-in-block numbers) board)
    {:numbers numbers
     :board-index (->> (map (partial find-match-in-block (drop-last numbers)) board)
                       (map-indexed vector)
                       (filter (fn [[_ v]] (empty? v)))
                       ffirst)}
    nil))

(defn- calculate-part [{numbers :numbers block :block :as part}]
  (println "Matching result" part)
  (let [filtered-nums (filter #(not (contains? (set numbers) %)) (flatten block))]
    (* (last numbers) (apply + filtered-nums))))

(defmethod main/advent 4 [_]
  (let [{board :board numbers-iterator :numbers-iterator} (read-input)
        part1 (some (partial find-first-match-on-board board) numbers-iterator)
        part2 (some (partial find-last-match-on-board board) numbers-iterator)]
    (println "Part 1:" (calculate-part part1)  " Part 2:" (calculate-part (assoc-in part2 [:block] (nth board (:board-index part2)))))
    ;; Part 1: 39902  Part 2: 26936
    ))