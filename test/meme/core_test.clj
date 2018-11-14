(ns meme.core-test
  (:require [clojure.test :refer :all]
            [meme.core :refer :all]))

(deftest test-get-round-robin-words
  (testing "get-round-robin-wordsは"
    (testing "先頭の文字を取得してリストで返す"
      (is (= ["su"](get-round-robin-words ["switch" "user"] 1))))
    (testing "先頭の文字を取得してリストで返す"
      (is (= ["su" "sus" "swu" "swus"](get-round-robin-words ["switch" "user"] 2))))
    ))

(deftest test-takes
  (testing "takesは"
    (testing "プレフィックスのリストを返す"
      (is (= ["s" "sw"](takes 2 "switch"))))
    ))

(deftest test-include-words
  (testing "include-words"
    (testing "単語リストを含むものを返す"
      (is (= ["con" "cat"] (include-words "concat" ["con" "cat" "x"]))))
    ))

