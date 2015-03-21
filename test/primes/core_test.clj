(ns primes.core-test
  (:require [clojure.test :refer :all]
            [primes.core :refer :all]))

(def first-100-primes
  "The first hundred prime numbers, courtesy of the Interwebs."
  [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89
   97 101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179
   181 191 193 197 199 211 223 227 229 233 239 241 251 257 263 269 271
   277 281 283 293 307 311 313 317 331 337 347 349 353 359 367 373 379
   383 389 397 401 409 419 421 431 433 439 443 449 457 461 463 467 479
   487 491 499 503 509 521 523 541])

(deftest prime-test
  (testing "Prime number testing"
    (is (not (prime? 1)))
    (is (prime? 2))
    (is (prime? 5))
    (is (not (prime? 100)))
    (is (prime? 101)))
  (testing "Prime number generation"
    (is (= first-100-primes (take 100 prime-numbers)))))

(deftest multiplication-table-test
  (testing "Mulitplication table routines"
    (testing "Construction of multiplication table"
      (is (= [[1 2] [2 4]]
             (make-multiplication-table [1 2])))
      (is (= [[4 20 16] [20 100 80] [16 80 64]]
             (make-multiplication-table [2 10 8]))))
    (testing "Multiplication table output"
      (is (= (str "  | 1\n"
                  "--+--\n"
                  "1 | 1\n")
             (with-out-str (print-multiplication-table [1]))))
      (is (= (str "   |  3 |  2 |  10\n"
                  "---+----+----+----\n"
                  " 3 |  9 |  6 |  30\n"
                  " 2 |  6 |  4 |  20\n"
                  "10 | 30 | 20 | 100\n")
             (with-out-str (print-multiplication-table [3 2 10])))))))

(deftest number-format-test
  (testing "Number formatting routines"
    (testing "Number width calculations"
      (is (= 1 (num-width 0)))
      (is (= 1 (num-width 1)))
      (is (= 2 (num-width 10)))
      (is (= 2 (num-width 99)))
      (is (= 4 (num-width 1000))))
    (testing "Greatest number length calculations"
      (is (= 1 (greatest-width (range 1 10))))
      (is (= 2 (greatest-width (range 1 100))))
      (is (= 3 (greatest-width [1 100]))))))
