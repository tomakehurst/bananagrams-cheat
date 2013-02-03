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

(fact "Single word definition to letter positions"
  (word-definition-to-letter-positions { :word "ant", :horizontal? true, :start-x 2, :start-y 3}) =>
    [{ :letter \a, :x 2, :y 3}
     { :letter \n, :x 3, :y 3}
     { :letter \t, :x 4, :y 3}])

(fact "All word definitions to de-duped letter postitions"
  (unique-letter-positions [{ :word "ant", :horizontal? true, :start-x 0, :start-y 2},
                            { :word "nanny", :horizontal? false, :start-x 1, :start-y 0}]) =>

    #{ { :letter \a, :x 0, :y 2}
       { :letter \n, :x 1, :y 2}
       { :letter \t, :x 2, :y 2}
       { :letter \n, :x 1, :y 0}
       { :letter \a, :x 1, :y 1}
       { :letter \n, :x 1, :y 3}
       { :letter \y, :x 1, :y 4} })


(def example-letter-positions #{{ :letter \a, :x 0, :y 2}
                                { :letter \n, :x 1, :y 2}
                                { :letter \t, :x 2, :y 2} })

(fact "Letter at position"
  (letter-at example-letter-positions 1 2) => \n
  (letter-at example-letter-positions 0 0) => \space
  (letter-at example-letter-positions 5 7) => \space)



;(fact "Letter coordinates for multpile words"
;  (to-letter-positions ))

;(fact "Renders board"
;  (render { "recursion" { :orientation :horizontal, :x 0, :y 1 }
;            "supper"    { :orientation :vertical, :x 3, :y 0 } }) => example-board)