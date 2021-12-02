(ns ghmulti.day02
  (:require
   [ghmulti.adventofcode2021 :as main]))


(defn- calculate_position [acc [direction val]]
  (case direction
    "forward" (update-in acc [:position] + (Integer/parseInt val))
    "up" (update-in acc [:depth] - (Integer/parseInt val))
    "down" (update-in acc [:depth] + (Integer/parseInt val))))

(defn- calculate_position_with_aim [acc [direction val]]
  (case direction
    "forward" (-> acc
                  (update-in [:position] + (Integer/parseInt val))
                  (update-in [:depth] + (* (:aim acc) (Integer/parseInt val))))
    "up" (update-in acc [:aim] - (Integer/parseInt val))
    "down" (update-in acc [:aim] + (Integer/parseInt val))))

(defmethod main/advent 2 [_]
  (let [
        lines (clojure.string/split-lines (slurp "resources/day02.txt"))
        ;; _ (println (take 5 lines))
        split_lines (map #(clojure.string/split % #" ") lines)
        ;; _ (println (take 5 split_lines))
        {target_position :position target_depth :depth :as target_pos} (reduce calculate_position {:depth 0 :position 0} split_lines)
        _ (println target_pos)
        {target_position_v2 :position target_depth_v2 :depth :as target_pos_v2} (reduce calculate_position_with_aim {:depth 0 :position 0 :aim 0} split_lines)
        _ (println target_pos_v2)
        ]
    (println "Part 1:" (* target_position target_depth) " Part 2:" (* target_position_v2 target_depth_v2))
    ))