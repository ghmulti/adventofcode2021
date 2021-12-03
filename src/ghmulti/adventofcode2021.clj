(ns ghmulti.adventofcode2021
  (:gen-class))
 
(defmulti advent :day)

(defmethod advent 0 [_]
  (println "Day 0 to come..."))

(defmethod advent :default [params]
  (println "Select day of advent to run: `clojure -M:run-m 1`" params))

(defn -main
  "I don't do a whole lot ... yet."
  [& [day & _]]
  (advent {:day day}))

(load "day01")
(load "day02")
(load "day03")