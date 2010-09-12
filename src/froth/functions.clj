(ns froth.functions
  (:use [froth core dictionary stack compile])
  (:import [java.io BufferedReader]))

(defn dictionary-defaults [{:keys [#^BufferedReader input]}]
  "Setup some built-in functions"
  (add-word "dup"   #(push-stack (peek-stack)))
  (add-word "+"     #(push-stack (+ (pop-stack) (pop-stack))))
  (add-word "-"     #(push-stack (let [a (pop-stack) b (pop-stack)] (- b a ))))
  (add-word "*"     #(push-stack (* (pop-stack) (pop-stack))))
  (add-word "/"     #(push-stack (let [a (pop-stack) b (pop-stack)] (/ b a ))))
  (add-word "swap"  #(let [a (pop-stack) b (pop-stack)] (push-stack a) (push-stack b)))
  (add-word "."     #(println (pop-stack)))
  (add-word ".S"    #(println @stack))
  (add-word "exit"  #(System/exit 0))
  ; TODO: fixme
  (add-word ":"     #(let [definition (read-definition input)
			   name (first definition)
			   code (rest definition)]
		       (add-word name (fn []
					(for [word (compile-words code)]
					  ((:fn word)))))))
  (alias-word "print" ".")
  (alias-word "print-stack" ".S")

  ; line comment
  (add-word "\\"    #(.readLine input) true))