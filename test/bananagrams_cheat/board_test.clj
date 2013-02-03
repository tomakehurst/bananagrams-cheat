(ns bananagrams-cheat.board_test
  (:use midje.sweet)
  (:use bananagrams-cheat.board))

(def example-board
"
   s
recursion
   p
   p
   e
   r
")

(fact "Letter with coordinates"
  (to-letter-with-coord true 2 7 3 \t) => { :letter \t, :x 5, :y 7}
  (to-letter-with-coord false 4 3 6 \o) => { :letter \o, :x 4, :y 9})

(fact "Getting letters with coordinates from word definition"
  (word-definition-to-letter-positions { :word "ant", :orientation :horizontal, :start-x 2, :start-y 3}) =>
    [{ :letter \a, :x 2, :y 3}
     { :letter \n, :x 3, :y 3}
     { :letter \t, :x 4, :y 3}])

;(fact "Renders board"
;  (render { "recursion" { :orientation :horizontal, :x 0, :y 1 }
;            "supper"    { :orientation :vertical, :x 3, :y 0 } }) => example-board)