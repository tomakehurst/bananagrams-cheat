(ns bananagrams-cheat.board)

(defn to-letter-with-coord [horizontal? start-x start-y index letter]
  { :letter letter,
    :x (if horizontal? (+ start-x index) start-x),
    :y (if horizontal? start-y (+ start-y index)) })

(defn word-definition-to-letter-positions
  [{:keys [word horizontal? start-x start-y]}]
  "Convert a word definition to a sequence of letter positions.
  A word definition looks like this:
  { :word \"recursion\", :horizontal? true, :start-x 0, :start-y 1 }"
    (map-indexed (partial to-letter-with-coord horizontal? start-x start-y) word))

(defn unique-letter-positions [word-definitions]
  (set (mapcat word-definition-to-letter-positions word-definitions)))

(defn add-to-board [board word intersect-letter-index with-letter-index])

(defn letter-at [letter-positions x y]
  (get (first (filter
                    #(and (= (:x %1) x) (= (:y %1) y))
                    letter-positions))
    :letter
    \space)) ; Return space by default

(defn render [word-definitions]
  )