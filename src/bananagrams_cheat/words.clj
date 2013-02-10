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

(defn first-index-of [letter letters]
  ((first
     (filter #(= (%1 1) letter)
       (map-indexed (fn [index item] [index item]) letters))) 0))

(defn remove-nth [n coll]
  (keep-indexed #(if (not= n %1) %2) coll))

(defn remove-first-instance-of [letter letters]
  (remove-nth (first-index-of letter letters) letters))

(defn remove-letters-in-word [word letters]
  (if (seq word)
    (let [current-letter (first word)
          remaining-letters (remove-first-instance-of current-letter letters)]
      (recur (rest word) remaining-letters))
    letters))