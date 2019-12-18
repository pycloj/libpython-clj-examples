(ns basic-tests.utils
  (:import (java.io File)))

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

(defn matplotlib->svg [f]
  (let [file (File/createTempFile "matplotlib" ".svg")
        path (.getAbsolutePath file)]
    (plt/figure)
    (f)
    (plt/savefig path)
    (let [result (slurp path)]
      (.delete file)
      result)))
