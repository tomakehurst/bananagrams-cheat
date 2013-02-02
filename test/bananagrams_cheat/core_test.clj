(ns bananagrams-cheat.core-test
  (:use midje.sweet)
  (:use bananagrams-cheat.core))

(defn lower-case-letter [char]
  (some #(= %1 (int char)) (range (int \a) (int \z))))

(fact "Single random letter"
  (single-random-letter) => lower-case-letter)

(fact "Random letter seq"
  (take 3 (random-letters)) => (just [\c \c \c])
  (provided (rand-25) => 2 ))

(fact "Word contains"
  (word-contains [\t \g \i] "things") => true
  (word-contains [\t \g \i] "stuff") => false)


(fact "Find word containing"
  (find-word-containing [\a \y \d]) => "abandonedly")

(fact "Letter counts"
  (letter-counts "aluminium") => { \a 1, \i 2, \l 1, \m 2, \n 1 \u 2 })

(fact "Word can be made with"
  (word-can-be-made-with [\a \e \p \p \p \a \l] "apple") => true
  (word-can-be-made-with [\a \e \p \b \p \a \l] "banana") => false)

(fact "First word of specific length makeable from letters"
  (bananagrams-cheat.core/first-word-makeable-with-letters [\t \b \i \e \f \f \c \y \u \e] 6) => "buffet")