(ns basic-tests.class-or-module
  (:require [notespace.note :as note
             :refer [note note-void
                     note-md note-hiccup
                     note-as-md note-as-hiccup]]))




(note-md "Experimenting with libpython-clj 1.28")
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


(note
 (initialize!))

(note-md "the dot in python has multiple meanings
what are the elements between the dots :

tfds.Split.TRAIN.subsplit

In the following tutorial:  https://www.tensorflow.org/tutorials/keras/classification

We find the following code:
```python
import tensorflow_datasets as tfds
train_validation_split = tfds.Split.TRAIN.subsplit ([6, 4])
```
We can assume that subsplit is a method 
But what are Split and TRAIN?
Are they modules, classes?

```python
import tensorflow_datasets as tfds
type (tfds.Split)
<class 'type'>
type (tfds.Split.TRAIN)
<class 'tensorflow_datasets.core.splits.NamedSplit'>
```

The best way to implement in clojure
")
 


(note 
 (py/import-as tensorflow tf)
 (py/import-as tensorflow_datasets tfds)
 (def train ( py/$.. tfds Split TRAIN ))
 (py/call-attr train "subsplit" '(6 4)))
(note/render-this-ns!)