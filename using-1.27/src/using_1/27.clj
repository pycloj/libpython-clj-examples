(ns using-1.27
  
  
  (:require [libpython-clj.require :as req :refer [require-python]
             :reload true]
            [libpython-clj.python
             :refer [as-python as-jvm
                     ->python ->jvm
                     get-attr call-attr call-attr-kw
                     get-item att-type-map
                     call call-kw initialize!
                     as-numpy as-tensor ->numpy
                     run-simple-string
                     add-module module-dict
                     import-module
                     python-type] :as py]))
;; => nil

;; => Syntax error (ClassNotFoundException) compiling at (27.clj:4:3).
;;    libpython-clj.require
;;    
;;    Syntax error reading source at (REPL:16:37).
;;    Unmatched delimiter: )


(initialize!)
;; => :ok


; (require-python '[tensorflow
;                   :reload true])
; (require-python '[tensorflow.keras
;                   :reload true])


(py/import-as tensorflow tf)
(py/import-as tensorflow.keras keras)
(py/import-as matplotlib.pyplot plt)
(py/import-as keras.datasets.fashion_mnist fashion_mnist)
; (py/import-from keras.datasets.fashion_mnist load_data)

(py/$. tf __version__)
(py/$. keras __version__)
; (py/$. plt __version__)

; (datasets = (py/$. keras datasets))
(def fashion_mnist_data (py/call-attr fashion_mnist "load_data"))
fashion_mnist_data



;; => Syntax error compiling at (27.clj:39:1).
;;    Unable to resolve symbol: keras in this context




;"
;; => Syntax error compiling at (27.clj:35:1).
;;    No such namespace: py


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-simple-string "print('hey')"))
