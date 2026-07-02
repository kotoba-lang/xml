(ns xml.core-test
  (:require [clojure.test :refer [deftest is]]
            [kotoba.xml :as kxml]
            [xml.core :as xml]))

(deftest hiccup-to-xml
  (is (= "<input name=\"value\" type=\"color3\" value=\"1, 0, 0\" />"
         (xml/xml [:input {:name "value" :type "color3" :value "1, 0, 0"}])))
  (is (= "<output name=\"out\" type=\"color3\" nodename=\"c\" />"
         (xml/xml [:output {:name "out" :type "color3" :nodename "c"}])))
  (is (= "<nodegraph name=\"NG\">\n  <output name=\"o\" type=\"color3\" />\n</nodegraph>"
         (xml/xml [:nodegraph {:name "NG"} [:output {:name "o" :type "color3"}]])))
  (is (= "<a title=\"x&quot;y\">\n  ok\n</a>" (xml/xml [:a {:title "x\"y"} "ok"])))
  (is (= "<a role=\"node\" />" (xml/xml [:a {:role :node}]))))

(deftest compatibility-namespace
  (is (= (xml/xml [:a "x"]) (kxml/xml [:a "x"]))))
