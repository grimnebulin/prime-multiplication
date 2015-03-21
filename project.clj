(defproject primes "0.1.0-SNAPSHOT"
  :description "Print a multiplication table of prime numbers"
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main ^:skip-aot primes.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
