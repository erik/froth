(ns froth.stack
  (:require [froth core]))

; stack and related functions
(def stack (ref []))

(defn pop-stack []
  "Removes and returns the last value on the stack"
  (dosync
   (let [value (peek @stack)]
     (alter stack pop)
     value)))

(defn push-stack [value]
  "Pushes a value onto the stack"
  (dosync (alter stack conj value)))

(defn peek-stack []
  "Returns the last value on the stack without changing it"
  (dosync (peek @stack)))

(defn clear-stack []
  "Clears the stack"
  (dosync (ref-set stack [])))