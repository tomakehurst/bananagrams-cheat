(ns bananagrams-cheat.core
  (:import (java.io BufferedReader StringReader)))

(defn rand-25 []
  (rand-int 25))

(defn single-random-letter []
  (char (+ (rand-25) (int \a))))

(defn random-letters []
  (lazy-seq (cons (single-random-letter) (random-letters))))

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

(def words
  (line-seq
    (BufferedReader. (StringReader. (slurp "/usr/share/dict/words")))))

(defn find-word-containing [letters]
  (let [containing-letters (partial word-contains letters)]
    (first (filter containing-letters words))))


(defn first-word-makeable-with-letters [letters length]
  (let [can-be-made-with-letters (partial word-can-be-made-with letters)]
     (first
        (filter
           #(and (can-be-made-with-letters %1)  (= (count %1) length))
           words))))

