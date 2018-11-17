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

(deftest test-weighting-words
  (testing "weighting-wordsは"
    (testing "重みを付けて返す"
      (is (= [{:weight 7 :name "grep"}] (weighting-words ["grep"] []))))
    (testing "常用英単語に同名のものがあれば重みがつく"
      (is (= [{:weight 7 :name "ab"} {:weight 12 :name "grep"}] (weighting-words ["grep" "ab"] ["grep"]))))
    ))

(deftest test-main
  (testing "mainは"
    (testing "エラーが発生しない"
      (is (nil? (-main "global regular expression print"))))
    ))

