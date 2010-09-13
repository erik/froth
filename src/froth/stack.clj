(ns froth.stack
  (:require [froth core]))

; stack and related functions
(def stack (atom []))

(defn pop-stack []
  "Removes and returns the last value on the stack"
   (let [value (peek @stack)]
     (swap! stack pop)
     value))

(defn push-stack [value]
  "Pushes a value onto the stack"
  (swap! stack conj value))

(defn peek-stack []
  "Returns the last value on the stack without changing it"
  (peek @stack))

(defn clear-stack []
  "Clears the stack"
  (swap! stack (constantly [])))