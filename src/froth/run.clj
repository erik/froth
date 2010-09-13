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

(swap! froth-reader (constantly (Scanner. (read-line))))

(loop []
  (try
    (evaluate)
    (catch Exception e (println (.getMessage e))))
  
  (print prompt)
  (flush)
  
  (swap! froth-reader (constantly (Scanner. (read-line))))
  
  (recur))
  
