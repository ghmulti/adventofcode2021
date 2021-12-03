(ns ghmulti.day02
  (:require
   [ghmulti.adventofcode2021 :as main]))

(defn- freq [& args] (frequencies args))

(defn- most-common [{ones \1 zeros \0}] (cond (> ones zeros) \1 (> zeros ones) \0 :else \1))

(defn- least-common [{ones \1 zeros \0}] (cond (< ones zeros) \1 (< zeros ones) \0 :else \0))

(defn- multiply-binary [a b]
  (* (Integer/parseInt a 2) (Integer/parseInt b 2)))

(defn- calculate_rating [lines ind target_filter_func]
  (if (= 1 (count lines))
    (first lines)
    (let [lines_freq (apply map freq lines)
          target_filter (target_filter_func (nth lines_freq ind))
          filtered_lines (filter #(= (nth % ind) target_filter) lines)]
      (recur filtered_lines (inc ind) target_filter_func))))

(defmethod main/advent 3 [_]
  (let [lines (clojure.string/split-lines (slurp "resources/day03.txt"))
        ;; _ (println (take 5 lines))    

        ;; part 1
        lines_freq (apply map freq lines)
        nth_most_common (->> lines_freq
                 (map most-common)
                 (apply str))
        nth-lest-common (->> lines_freq
                     (map least-common)
                     (apply str))
        _ (println nth_most_common  " - " nth-lest-common)

        ;; part 2
        oxygen_rating (calculate_rating lines 0 most-common)
        co2_rating (calculate_rating lines 0 least-common)
        _ (println oxygen_rating " - " co2_rating)]
    
    (println "Part 1:" (multiply-binary nth_most_common nth-lest-common) " Part 2:" (multiply-binary oxygen_rating co2_rating)))
    ;; Part 1: 741950  Part 2: 903810
  )