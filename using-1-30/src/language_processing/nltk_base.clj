(ns language-processing.nltk-base
  (:require [notespace.v1.note :as note
             :refer [note note-void
                     note-md note-hiccup
                     note-as-md note-as-hiccup]]
            [libpython-clj.python :as py]))




(note-md "Experimenting with libpython-clj 1.30")
(note
 (require  '[libpython-clj.require :refer [require-python]]
           '[libpython-clj.python :as py]))


(note-md "Initial nltk example")


(note
 (require-python '(nltk)))

(node-md "## Download Data
The first time you work with nltk download the data.
select the location (use default location if you have a doubt)
")
;(note (nltk/download ))

(note-md " 
```python
from nltk.book import *
print (text1.concordance ('man'))
text1.dispersion_plot (['man', 'woamn'])
```
")
(note 
 (require-python '(nltk.book :refer :all))

(py/$a nltk.book/text2 concordance  "woman")

(py/$a nltk.book/text2 dispersion_plot (py/->py-list ["woman" "man"]))
 )

(note/render-this-ns!)


