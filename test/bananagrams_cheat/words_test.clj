(ns bananagrams-cheat.words_test
  (:use midje.sweet)
  (:use bananagrams-cheat.words))

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
  (first-word-makeable-with-letters [\t \b \i \e \f \f \c \y \u \e] 6) => "buffet")

(fact "Deduct from letter counts"
  (deduct-from-letter-counts { \s 3
                               \u 1
                               \m 2
                               \e 3
                               \r 6
                               \d 2
                               \f 1}
                               "summer") => {\s 2 \e 2 \r 5 \d 2 \f 1})

(fact "Remove letters in word"
  (remove-letters-in-word "thing" [\g \g \i \n \t \d \r \h \b]) => [\g \d \r \b])