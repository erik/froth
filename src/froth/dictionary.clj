(ns froth.dictionary
  (:use [froth core stack])
  (:import [java.io BufferedReader]))

; dictionary and related function
(def dictionary (ref {}))

(defn add-word
  "Adds a word to the dictionary"
  ([name fn immediate?]
     (if (dictionary name)
       (throw (Exception. (str "word already defined: \"" name "\"")))
       (dosync (alter dictionary assoc name {:fn fn :immediate? immediate? }))))
  ([name fn]
     (add-word name fn false)))

(defn remove-word [word]
  "Removes a word from the dictionary"
  (if (dictionary word)
    (dosync (alter dictionary dissoc word))
    (throw (Exception. (str "word not defined: \"" word "\"")))))

(defn alias-word [new old]
  "Add an alias for a dictionary definition"
  (if-let [word (dictionary old)]
    (add-word new (:fn word) (:immediate? word))
    (throw (Exception.  (str "word not defined: \"" old "\"")))))

(defn get-word [word]
  "Returns the value of the requested word"
  (if-let [w (dictionary word)]
    w
    (throw (Exception. (str "word not defined: \"" word "\"")))))


