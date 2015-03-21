(ns primes.core
  (:gen-class)
  (:require [clojure.string :refer [join]]))

(def prime-numbers
  "A lazy, infinite sequence of prime numbers."
  (let [prime (fn [n]
                (not (some #(zero? (mod n %))
                           (take-while #(<= (* % %) n) prime-numbers))))]
    (cons 2 (filter prime (iterate #(+ 2 %) 3)))))

(defn make-multiplication-table
  "Constructs a square vector-of-vectors representing a multiplication
  table for the numbers NUMS."
  [nums]
  (vec (map (fn [y] (vec (map (fn [x] (* x y)) nums))) nums)))

(defn num-width
  "Computes the number of digits in the base-10 representation of the
  number NUM."
  [num]
  (if (< num 10)
    1
    (inc (num-width (/ num 10)))))

(defn greatest-width
  "Of all of the numbers of digits in all of the numbers in NUMS,
  returns the largest."
  [nums]
  (apply max (map num-width nums)))

(defn print-multiplication-table
  "Print a multiplication table with the numbers NUMS in order along
  the horizontal and vertical axes."
  [nums]
  (let [table  (make-multiplication-table nums)
        widths (cons (greatest-width nums) (map greatest-width table))
        fmt    (join " | " (map #(str "%" % "s") widths))]
    (println (apply format fmt "" nums))
    (println (join "-+-" (map #(join (repeat % "-")) widths)))
    (doseq [row (map cons nums table)]
      (println (apply format fmt row)))))

(defn -main
  "Print a multiplication table for the first ten prime numbers."
  [& args]
  (print-multiplication-table (take 10 prime-numbers)))
