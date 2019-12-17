(ns using-1.27
  
  
  (:require '[libpython-clj.python
             :refer [as-python as-jvm
                     ->python ->jvm
                     get-attr call-attr call-attr-kw
                     get-item att-type-map
                     call call-kw initialize!
                     as-numpy as-tensor ->numpy
                     run-simple-string
                     add-module module-dict
                     import-module
                     python-type]]))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
