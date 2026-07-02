(ns kotoba.xml
  "Compatibility facade for xml.core (hiccup -> XML) and xml.parse
  (XML -> hiccup)."
  (:require [xml.core :as xml]
            [xml.parse :as parse]))

(def xml xml/xml)
(def parse parse/parse)
(def unescape parse/unescape)
