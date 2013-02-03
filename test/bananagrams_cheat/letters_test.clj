(ns bananagrams-cheat.letters_test
  (:use midje.sweet)
  (:use bananagrams-cheat.letters))

(defn lower-case-letter [char]
  (some #(= %1 (int char)) (range (int \a) (int \z))))

(fact "Single random letter"
  (single-random-letter) => lower-case-letter)

(fact "Random letter seq"
  (take 3 (random-letters)) => (just [\c \c \c])
  (provided (rand-25) => 2 ))