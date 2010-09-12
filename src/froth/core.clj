(ns froth.core
  (:import [java.io BufferedReader]
	   [java.util Scanner]))

(def *froth-version* "0.1.0")
(def *froth-reader* (ref 0))

(defn to-number [string]
  "Converts string into an Integer or Float"
  (if (.contains string ".")
    (Float. string)
    (Integer. string)))

(defn- read-word- [s #^BufferedReader reader]
  "Private function. Ignore me"
  (let [scanner  @*froth-reader*]
    (if (.hasNext scanner)
      (.next scanner)
      "")))

(defn read-word [#^BufferedReader reader]
  "Reads the next word from reader"
  (let [word (read-word- "" reader)]
    (if (or (empty? word) (seq (re-matches #"\s" word)))
      :EOF
      (try
	(do (to-number word))
	(catch NumberFormatException _ word)))))

(defn read-definition- [#^BufferedReader reader words]
  "Private helper to read-definition. Ignore me."
  (let [word (read-word @*froth-reader*)]
    (if (or (= word ";") (= word :EOF))
      words
      (read-definition- reader (conj words word)))))

(defn read-definition [#^BufferedReader reader]
  "Reads the definition of a function (upto and not including the ';')"
  (read-definition- reader []))
    
    
