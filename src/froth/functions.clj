(ns froth.functions
  (:use [froth core dictionary stack compile])
  (:require clojure.contrib.pprint)
  (:import [java.io BufferedReader]))

(defn dictionary-defaults []
  "Setup some built-in functions"
  (add-word "dup"   #(push-stack (peek-stack)))
  (add-word "+"     #(push-stack (+ (pop-stack) (pop-stack))))
  (add-word "-"     #(push-stack (let [a (pop-stack) b (pop-stack)] (- b a ))))
  (add-word "*"     #(push-stack (* (pop-stack) (pop-stack))))
  (add-word "/"     #(push-stack (let [a (pop-stack) b (pop-stack)] (/ b a ))))
  (add-word "swap"  #(let [a (pop-stack) b (pop-stack)] (push-stack a) (push-stack b)))
  (add-word ">"     #(push-stack (< (pop-stack) (pop-stack))))
  (add-word "<"     #(push-stack (> (pop-stack) (pop-stack))))
  (add-word "="     #(push-stack (= (pop-stack) (pop-stack))))  
  (add-word "."     #(println (pop-stack)))
  (add-word ".S"    #(clojure.contrib.pprint/pprint @stack))
  (add-word "0SP"   #(clear-stack))
  (add-word "exit"  #(System/exit 0))
  (add-word ":"     #(let [definition (read-definition)
			   name (first definition)
			   code (rest definition)]
		       (add-word name (fn []
					(for [word (compile-words code)]
					  ((:fn word)))))))
  (add-word ".\""   #(push-stack (read-str "\"")))
  (add-word ".("    #(push-stack (read-str ")")))
  (add-word "alias" #(alias-word (pop-stack) (pop-stack)))
  (add-word "undef" #(remove-word (pop-stack)))

  (add-word "true"  #(push-stack true))
  (add-word "false" #(push-stack false))
  (add-word "?"     #(push-stack (boolean (pop-stack))))
  (add-word "and"   #(push-stack (boolean (and (pop-stack) (pop-stack)))))
  (add-word "or"    #(push-stack (boolean (or (pop-stack) (pop-stack)))))
    
  (alias-word "print" ".")
  (alias-word "print-stack" ".S")
  (alias-word "clear" "0SP")
  (alias-word "def" ":")
  (alias-word "true?" "?")

  ; line comment
  (add-word "\\"    #(.readLine @*froth-reader*) true)
  ; inline comment
  (add-word "("     #(read-str ")") true))