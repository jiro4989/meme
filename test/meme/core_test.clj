(ns meme.core-test
  (:require [clojure.test :refer :all]
            [meme.core :refer :all]))

(def m {:weight 1 :name "cat"})

(deftest test-format-line
  (testing "format-lineは"
    (testing "通常出力"
      (is (= "  1:cat" (format-line m {:none false :padding-size 3 :delimiter \:}))))
    (testing "none指定ありの場合は数値を返さない"
      (is (= "cat" (format-line m {:none true :padding-size 3 :delimiter \:}))))
    (testing "区切り文字を指定できる"
      (is (= "  1,cat" (format-line m {:none false :padding-size 3 :delimiter ","}))))
    ))

