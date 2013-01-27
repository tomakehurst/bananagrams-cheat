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

(def word-seq
  (line-seq
    (BufferedReader. (StringReader. (slurp "/usr/share/dict/words")))))

(defn find-word-containing [letters]
  (with-open [rdr (clojure.java.io/reader "/usr/share/dict/words")]
    (let [words (line-seq rdr)
          containing-letters (partial word-contains letters)]
      (first (filter containing-letters words)))))
