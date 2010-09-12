(ns froth.compile
  (:use [froth core dictionary]))

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
  (let [word (read-word reader)
	word-def (get-word word)]
    ((:fn word-def))))