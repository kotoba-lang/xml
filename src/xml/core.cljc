(ns xml.core
  "Hiccup to XML emitter."
  (:require [clojure.string :as str]))

(defn- esc [s] (-> (str s) (str/replace "&" "&amp;") (str/replace "<" "&lt;") (str/replace ">" "&gt;")))
(defn- esc-attr [s] (str/replace (esc s) "\"" "&quot;"))
(defn- attrs [m]
  (apply str (for [[k v] m]
               (str " " (name k) "=\"" (esc-attr (if (keyword? v) (name v) v)) "\""))))

(declare emit)
(defn- emit [form ind]
  (let [pad (apply str (repeat ind "  "))]
    (if (vector? form)
      (let [[tag & more] form
            attr (when (map? (first more)) (first more))
            children (if attr (rest more) more)]
        (if (seq children)
          (str pad "<" (name tag) (attrs attr) ">\n"
               (str/join "\n" (map #(emit % (inc ind)) children))
               "\n" pad "</" (name tag) ">")
          (str pad "<" (name tag) (attrs attr) " />")))
      (str pad (esc form)))))

(defn xml
  "Compile a hiccup form to an indented XML string."
  [form]
  (emit form 0))
