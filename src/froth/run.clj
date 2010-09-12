(ns froth.run
  (:use [froth core stack dictionary compile functions])
  (:import [java.io BufferedReader StringReader]
	   [java.util Scanner]))

;; REPL
(def prompt "> ")

(dictionary-defaults) 

(println "froth version" *froth-version*)
(println "Copyright (c) 2010, Erik Price")

(print prompt)
(flush)

(dosync
 (ref-set *froth-reader* (Scanner. (read-line))))

(loop []
  (try
    (evaluate)
    (catch Exception e (println (.getMessage e))))
  
  (print prompt)
  (flush)
  
  (dosync
   (ref-set *froth-reader* (Scanner. (read-line))))
  
  (recur))
  
