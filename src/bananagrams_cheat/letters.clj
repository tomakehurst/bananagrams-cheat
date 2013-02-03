(ns bananagrams-cheat.letters)

(defn rand-25 []
  (rand-int 25))

(defn single-random-letter []
  (char (+ (rand-25) (int \a))))

(defn random-letters []
  (lazy-seq (cons (single-random-letter) (random-letters))))
