(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn parse-tag [data]
    (let [[tag attrs & siblings] data ]
        (cond 
            (= (count siblings) 0) ()
            (not= (get attrs :class) "r") (flatten (map parse-tag (filter vector? siblings)))
            :else (get (get (get data 2) 1) :href))))

(defn get-links []
  (let [data (parse "clojure_google.html")]
    (vec (parse-tag data))))

(defn -main []
  (println (str "Found " (count(get-links)) " links!")))
