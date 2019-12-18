(ns basic-tests.keras-test
  
  
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
(py/import-as keras.layers keras-layers)

(py/$. tf __version__)
(py/$. keras __version__)
; (py/$. plt __version__)

; (datasets = (py/$. keras datasets))
(def fashion_mnist_data (py/call-attr fashion_mnist "load_data"))
fashion_mnist_data
(defmacro def+
  "binding => binding-form
  internalizes binding-forms as if by def.
  See http://clojuredocs.org/clojure.core/destructure ."
  {:added "1.9", :special-form true, :forms '[(def+ [bindings*])]}
  [& bindings]
  (let [bings (partition 2 (destructure bindings))]
    (sequence cat
              ['(do)
               (map (fn [[var value]] `(def ~var ~value)) bings)
               [(mapv (fn [[var _]] (str var)) bings)]])))

(def+ [[train-images , train-labels], [test-images, test-labels]] (vec fashion_mnist_data))


(def class-names  (py/->py-list ["T-shirt/top", "Trouser", "Pullover", "Dress", "Coat"
                    "Sandal", "Shirt", "Sneaker", "Bag", "Ankle boot"]))


(py/$. train-images shape)

; learn how 
(require-python '[builtins :refer [len]] )
(len train-images)
train-labels

(py/$. test-images shape)

(len test-images)
(len test-labels)


; (def mfig (py/import-module "matplotlib.figure"))
; (def plt (py/import-module "matplotlib.pyplot"))
; (def fig (py/call-attr plt "figure"))
;(py/call-attr plt "figure")
; (py/call-attr plt "imshow" (train_images [0]))
; (py/as-list class_names)
;; => Syntax error compiling at (27.clj:39:1).
;;    Unable to resolve symbol: keras in this context

;; the following code fails - consider using pantera 
; (train-images (/ train-images 255.0))
; (test-images (/ test-images 255.0))

(def flatten (py/call-attr-kw keras-layers "Flatten" [] {:input_shape   [28,28]}))
(def dense (py/call-attr-kw keras-layers "Dense" [128] {:activation  "relu"}))
(def last-dense (py/call-attr-kw keras-layers "Dense" [10] {:activation "softmax"}))


(defonce model (py/call-attr  keras "Sequential" [ (py/call-attr-kw keras-layers "Flatten" [] {:input_shape   [28,28]}) ]))
;TypeError: The added layer must be an instance of class Layer. Found: <keras.layers.core.Flatten object at 0x667080590>

(defonce model (py/call-attr  keras "Sequential" [ (py/call-attr-kw keras-layers "Layer" [] {:input_shape   [28,28]}) ]))
;TypeError: The added layer must be an instance of class Layer. Found: <keras.engine.base_layer.Layer object at 0x662ac1890>


; (py/call-attr model "add"   flatten )

; (py/call-attr model "add"  (py/call-attr-kw keras-layers "Dense" [128] {:activation  "relu"}) )
; (py/call-attr model "add"  (py/call-attr-kw keras-layers "Dense" [10] {:activation "softmax"}) )
; (def model (py/call-attr  keras "Sequential" [(py/call-attr-kw keras-layers "Flatten" [] {:input_shape   [28,28]})
;                                               (py/call-attr-kw keras-layers "Dense" [128] {:activation  "relu"})
;                                               (py/call-attr-kw keras-layers "Dense" [10] {:activation "softmax"})]))


(py/call-attr-kw  model "compile" [] {:optimizer "adam"
                                      :loss "sparse_categorical_crossentropy"
                                      :metrics (py/->py-list ["accuracy"])})
;; => Syntax error compiling at (27.clj:91:1).
;;    Unable to resolve symbol: train-images in this context

;"
;; => Syntax error compiling at (27.clj:35:1).
;;    No such namespace: py


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-simple-string "print('hey')"))
