(ns primes.core
  (:gen-class)
  (:require [clojure.string :refer [join]]))

(declare prime-numbers)

(defn prime?
  "Test if the number N is prime."
  [n]
  (and (> n 1)
       (not (some #(zero? (mod n %))
                  (take-while #(<= (* % %) n) prime-numbers)))))

(def prime-numbers
  "A lazy, infinite sequence of prime numbers."
  (cons 2 (filter prime? (iterate #(+ 2 %) 3))))

(defn make-multiplication-table
  "Constructs a square vector-of-vectors representing a multiplication
  table for the numbers NUMS."
  [nums]
  (vec (map (fn [y] (vec (map (fn [x] (* x y)) nums))) nums)))

(defn num-width
  "Computes the number of characters in the base-10 representation of
  the number NUM."
  [num]
  (if (neg? num)
    (inc (num-width (- num)))
    (loop [num num len 1]
      (if (< num 10)
        len
        (recur (/ num 10) (inc len))))))

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
    (println (join "-+-" (map #(String. (char-array % \-)) widths)))
    (doseq [row (map cons nums table)]
      (println (apply format fmt row)))))

(defn -main
  "Print a multiplication table for the first ten prime numbers."
  [& args]
  (print-multiplication-table (take 10 prime-numbers)))
