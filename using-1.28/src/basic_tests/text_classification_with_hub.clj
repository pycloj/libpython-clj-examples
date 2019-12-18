(ns basic-tests.text-classification-with-hub
  "based on the tutorial https://www.tensorflow.org/tutorials/keras/text_classification_with_hub"

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

(initialize!)

(py/import-as numpy np)
(py/import-as tensorflow tf)
(py/import-as tensorflow_hub hub)
(py/import-as tensorflow_datasets tfds)
;; => Execution error at libpython-clj.python.interpreter/check-error-throw (interpreter.clj:500).
;;    ModuleNotFoundError: No module named 'tensorflow_datasets'
;;    




(println ["Version: " (py/$. tf __version__)])
; (println ["Eager mode: "  (py/call-attr tf "executing_eagerly")])
; (println ["GPU"  tf.config.experimental.list_physical_devices ("GPU")])
(println ["Hub Version: " (py/$. hub __version__)])


(def train ( py/$.. tfds Split TRAIN ))
(py/call-attr train "subsplit" '(6 4))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-simple-string "print('hey')"))
