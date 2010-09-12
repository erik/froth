(ns froth.core
  (:import [java.io BufferedReader]))

(def *froth-version* "0.1.0")

(defn to-number [string]
  "Converts string into an Integer or Float"
  (if (.contains string ".")
    (Float. string)
    (Integer. string)))

(defn- read-word- [s #^BufferedReader reader]
  "Private function. Ignore me"
  (let [i (.read reader)]
	; check for EOF
    (if (= i -1)
      s
      (let [c (str (char i))]
	(if-not (seq (re-matches #"\s" c))
	  (read-word- (str s c) reader)
	  s)))))

(defn read-word [#^BufferedReader reader]
  "Reads the next word from reader"
  (let [word (read-word- "" reader)]
    (if (= "" word)
      :EOF
      (try
	(do (to-number word))
	(catch NumberFormatException _ word)))))

(defn read-definition- [#^BufferedReader reader words]
  "Private helper to read-definition. Ignore me."
  (let [word (read-word reader)]
    (if (= word ";")
      words
      (read-definition- reader (conj words word)))))

(defn read-definition [#^BufferedReader reader]
  "Reads the definition of a function (upto and not including the ';')"
  (read-definition- reader []))
    
    
