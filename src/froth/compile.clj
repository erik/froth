(ns froth.compile
  (:use [froth core dictionary stack ])
  (:import [java.io BufferedReader]))

(defn compile-words- [words evals]
  "Private helper to compile-words, ignore me"
  (if-let [word (first words)]
    (do
      (let [word-def (get-word word)]
	(if (:immediate? word-def)
	  ((:fn word-def))
	  (compile-words- (rest words) (conj evals ((:fn word-def)))))))
    evals))

(defn compile-words [words]
  "Takes a vector of words, and evaluates immediate ones (for use with ':')"
  (compile-words- words []))

(defn evaluate [#^BufferedReader reader]
  "Evaluates the next word from the reader"
  (loop []
    (let [word (read-word reader)]
      (if (= (class word) String)
	((:fn (get-word word)))
	(when (not= word :EOF)
	  (push-stack word)))
      (when (not= word :EOF)
	(recur)))))
  