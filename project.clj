(defproject bananagrams-cheat "0.1-SNAPSHOT"
  :description "Cheating at Bananagrams"
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :plugins [[lein-idea "1.0.1"]]
  :profiles {
              :dev {
                     :dependencies [[midje "1.4.0"]
                                   [bultitude "0.1.7"]]
                     :plugins [[lein-midje "2.0.4"]]
              }
  }

  :jvm-opts ["-Djava.util.Arrays.useLegacyMergeSort=true"]
)
