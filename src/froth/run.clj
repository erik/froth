(ns froth.run
  (:use [froth core stack dictionary compile functions])
  (:import [java.io BufferedReader StringReader]
	   [java.util Scanner]))

(def prompt "> ")

(dictionary-defaults)

(defn repl []
  "The froth REPL"
  (println "froth version" *froth-version*)
  (println "Copyright (c) 2010, Erik Price")
  
  (print prompt)
  (flush)
  
  (swap! froth-reader (constantly (Scanner. (read-line))))
  
  (while true
    (try
      (evaluate)
      (catch Exception e (println (.getMessage e))))
    
    (print prompt)
    (flush)
    
    (swap! froth-reader (constantly (Scanner. (read-line))))))

(defn read-file [args]
  "Reads files named by args in order"
  (doseq [file (seq args)]
    (try
      (swap! froth-reader (constantly (Scanner. (slurp file))))
      (evaluate)
      (catch Exception e (println (.getMessage e)) (System/exit 1)))))

(if (seq *command-line-args*)
  (read-file *command-line-args*)
  (repl))