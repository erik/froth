(ns froth.core
  (:import [java.io BufferedReader]
	   [java.util Scanner]
	   [java.util.regex Pattern]))

(def *froth-version* "0.2.0")
(def froth-reader (atom 0))

(defn to-number [string]
  "Converts string into an Integer or Float"
  (if (.contains string ".")
    (Float. string)
    (Integer. string)))

(defn- read-word- []
  "Private function. Ignore me"
  (let [scanner  @froth-reader]
    (if (.hasNext scanner)
      (.next scanner)
      "")))

(defn read-word []
  "Reads the next word from reader"
  (let [word (read-word-)]
    (if (or (empty? word) (seq (re-matches #"\s" word)))
      :EOF
      (try
	(do (to-number word))
	(catch NumberFormatException _ word)))))

(defn read-definition- [words]
  "Private helper to read-definition. Ignore me."
  (let [word (read-word)]
    (if (or (= word ";") (= word :EOF))
      words
      (read-definition- (conj words word)))))

(defn read-definition []
  "Reads the definition of a function (upto and not including the ';')"
  (read-definition- []))
    

(defn read-str [delim]
  "Returns a string, delimited by delim"
  (let [original-delim (.delimiter @froth-reader)]
    ; skip over leading whitespace
    (.skip @froth-reader #"\s")
    (.useDelimiter @froth-reader (Pattern/quote delim))
    (let [string (read-word)]
      (when-not string
	(throw (Exception. "Unterminated string!")))
      
      ; skip over trailing 'delim'
      (.skip @froth-reader (Pattern/quote delim))
      
      (.useDelimiter @froth-reader original-delim)
      string)))