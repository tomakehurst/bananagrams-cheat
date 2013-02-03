(ns bananagrams-cheat.board)

(defn to-letter-with-coord [horizontal? start-x start-y index letter]
  { :letter letter,
    :x (if horizontal? (+ start-x index) start-x),
    :y (if horizontal? start-y (+ start-y index)) })

(defn word-definition-to-letter-positions [word-definition]
  "Convert a word definition to a sequence of letter positions.
  A word definition looks like this:
  { :word \"recursion\", :orientation :horizontal, :start-x 0, :start-y 1 }"
  (let [horizontal? (= (word-definition :orientation) :horizontal)
        word (word-definition :word)]
    (map-indexed (partial to-letter-with-coord horizontal? (:start-x word-definition) (:start-y word-definition)) word)))

(defn to-letter-positions [board-definition]
  )

(defn add-to-board [board word intersect-letter-index with-letter-index])


(defn render [board]
  )