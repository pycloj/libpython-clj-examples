(ns language-processing.nltk-base
  (:require [notespace.v1.note :as note
             :refer [note note-void
                     note-md note-hiccup
                     note-as-md note-as-hiccup]]
            [libpython-clj.python :as py]))




(note-md "Experimenting od python spacy with libpython-clj 1.30


install:
```
conda install -c conda-forge spacy
```
Download english 
`python -m spacy download en_core_web_sm`
Download Italian
`python -m spacy download it_core_news_sm`

")
(note
 (require  '[libpython-clj.require :refer [require-python]]
           '[libpython-clj.python :as py]))


(note-md "Initial spacy example")



 (require-python '(spacy))


(note-md " 
```python
from nltk.book import *
print (text1.concordance ('man'))
text1.dispersion_plot (['man', 'woamn'])
for token in doc:
    print(token.text, token.lemma_, token.pos_, token.tag_, token.dep_,
            token.shape_, token.is_alpha, token.is_stop)

```
")


 
 (def nlp (py/$a spacy load "en_core_web_sm" ))

(def doc ( nlp  "Apple is looking at buying U.K. startup for $1 billion"))
doc

(for [token doc] [(py/$. token text) (py/$. token pos)])

(-> doc first (py/$. lemma_)) 
(note/render-this-ns!)


