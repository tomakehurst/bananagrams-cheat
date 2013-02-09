(ns bananagrams-cheat.words
  (:import (java.io BufferedReader StringReader)))

(def words
  (line-seq
    (BufferedReader. (StringReader. (slurp "/usr/share/dict/words")))))

(defn word-contains [letters word]
  (every? #(.contains word (str %1)) letters))

(defn inc-char-count [count-map char]
  (assoc count-map char (inc (get count-map char 0))))

(defn letter-counts [characters]
  (into (sorted-map) (reduce inc-char-count {} (seq characters))))

(defn word-can-be-made-with [letters word]
  (let [word-letter-counts (letter-counts word)
        candidate-letter-counts (letter-counts letters)
        letter #(get %1 0)
        letter-count #(get %1 1)]
    (every? #(>= (candidate-letter-counts (letter %1) 0) (letter-count %1)) word-letter-counts)))

(defn find-word-containing [letters]
  (let [containing-letters (partial word-contains letters)]
    (first (filter containing-letters words))))

(def by-descending-length #(>= (count %1) (count %2)))

(defn words-makeable-with-letters [letters]
  (let [can-be-made-with-letters (partial word-can-be-made-with letters)]
    (sort by-descending-length (filter #(can-be-made-with-letters %1) words))))

(defn first-word-makeable-with-letters [letters length]
  (first (filter #(= (count %1) length) (words-makeable-with-letters letters))))

(defn count-instances-of-letter [word letter]
  (count (filter #(= letter %1) word)))

(defn subtract-from-letter-count [word letter-count]
  (let [letter (letter-count 0)
        starting-quantity (letter-count 1)
        subtract-quantity (count-instances-of-letter word letter)]
    [letter (- starting-quantity subtract-quantity)]))

(defn deduct-from-letter-counts [letter-counts word]
  (into {}
    (remove
    #(< (%1 1) 1)
    (map (partial subtract-from-letter-count word) letter-counts))))