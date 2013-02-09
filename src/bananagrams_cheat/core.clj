(ns bananagrams-cheat.core
  (:require [bananagrams-cheat.letters :refer :all])
  (:require [bananagrams-cheat.words :refer :all])
  (:require [bananagrams-cheat.board :refer :all]))

(defn remove-word-from-letters [word letters]
  {:pre (word-can-be-made-with letters word)}
  ())


(defn add-one-word-to-board [board available-letters]
  ()
  )