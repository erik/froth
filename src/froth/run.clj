(ns froth.run
  (:use [froth core stack dictionary compile functions])
  (:import [java.io BufferedReader StringReader]))

;; REPL
(def prompt "> ")

(dictionary-defaults {:input
		      (BufferedReader. *in*)}) 

(println "froth version" *froth-version*)
(println "Copyright (c) 2010, Erik Price")

(print prompt)
(flush)

(loop [reader (BufferedReader. (StringReader. (read-line)))]
  ;(try
    (evaluate reader)
   ; (catch Exception e (println (class e) (.getMessage e))))
  (print prompt)
  (flush)
  (recur (BufferedReader. (StringReader. (read-line)))))