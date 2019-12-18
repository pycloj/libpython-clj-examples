(ns basic-tests.keras-test
  (:require [notespace.note :as note
             :refer [note note-void
                     note-md note-hiccup
                     note-as-md note-as-hiccup]]))

(note
 (require '[libpython-clj.require :as req :refer [require-python]
            :reload true]
          '[libpython-clj.python
            :refer [as-python as-jvm
                    ->python ->jvm
                    get-attr call-attr call-attr-kw
                    get-item att-type-map
                    call call-kw initialize!
                    as-numpy as-tensor ->numpy
                    run-simple-string
                    add-module module-dict
                    import-module
                    python-type] :as py]
          '[basic-tests.utils :refer [def+ matplotlib->svg]]))

(note-md "Experimenting with libpython-clj 1.28,
mixing the different ways to consume python modules.

Based on: https://www.tensorflow.org/tutorials/keras/classification")

(note
 (initialize!))

(note-void
 (require-python '[builtins :refer [len]] )
 (py/import-as tensorflow tf)
 (py/import-as tensorflow.keras keras)
 (require-python '[matplotlib.pyplot :as plt])
 (py/import-as numpy np)
 (py/import-as tensorflow.keras.datasets.fashion_mnist fashion_mnist)
                                        ; (py/import-from keras.datasets.fashion_mnist load_data)
 (py/import-as tensorflow.keras.layers keras-layers))

(note
 (py/$. tf __version__)
 (py/$. keras __version__))

(note
 (def fashion_mnist_data (py/call-attr fashion_mnist "load_data")))

(note-void
 (def+ [[train-images , train-labels], [test-images, test-labels]]
   (vec fashion_mnist_data)))

(note train-images)
(note train-labels)
(note test-images)
(note test-labels)

(note
 (def class-names  (py/->py-list ["T-shirt/top", "Trouser", "Pullover", "Dress", "Coat"
                                  "Sandal", "Shirt", "Sneaker", "Bag", "Ankle boot"])))
(note
 (py/$. train-images shape))

(note
 [(len train-images)
  (len train-labels)])

(note
 (py/$. test-images shape))

(note
 [(len test-images)
  (len test-labels)])

(note-as-hiccup
 (matplotlib->svg
  (fn []
    (plt/imshow (first train-images))
    (plt/colorbar)
    (plt/grid false))))

(note
 (def test-images (py/call-attr np "divide" test-images 255.0)))

(note
 (def train-images (py/call-attr np "divide" train-images 255.0)))

(note
 (def model
   (py/call-attr
    keras "Sequential"
    [(py/call-attr-kw keras-layers "Flatten" [] {:input_shape [28,28]})
     (py/call-attr-kw keras-layers "Dense" [128] {:activation "relu"})
     (py/call-attr-kw keras-layers "Dense" [10] {:activation "softmax"})])))

(note
 (py/call-attr-kw
  model "compile"
  [] {:optimizer "adam"
      :loss      "sparse_categorical_crossentropy"
      :metrics   (py/->py-list ["accuracy"])}))

(note
 (py/call-attr-kw model "fit" [train-images, train-labels] {:epochs 3}))

(note
 (def test-res
   (py/call-attr-kw model "evaluate" [test-images test-labels] {:verbose 2})))


(note/render-this-ns!)

