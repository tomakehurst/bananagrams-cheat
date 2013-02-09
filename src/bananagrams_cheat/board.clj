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

(defn letter-at [letter-positions x y]
  (get (first (filter
                #(and (= (:x %1) x) (= (:y %1) y))
                letter-positions))
    :letter
    \space)) ; Return space by default

(defn lowest-value [letter-positions axis]
  (apply min (map #(axis %1) letter-positions)))

(defn highest-value [letter-positions axis]
  (apply max (map #(axis %1) letter-positions)))

(defn render [board]
  (let [letter-positions (unique-letter-positions board)
        min-x (lowest-value letter-positions :x)
        max-x (highest-value letter-positions :x)
        min-y (lowest-value letter-positions :y)
        max-y (highest-value letter-positions :y)]
    (apply str (for [y (range min-y (+ max-y 1))]
      (apply str
        (conj
          (map #(letter-at letter-positions %1 y) (range min-x (+ max-x 1)))
          \newline))))))

(defn index-of [word letter]
  (.indexOf (str letter)))

(defn add-to-board [board word word-to-intersect intersect-letter-index intersectee-letter-index]

  )


