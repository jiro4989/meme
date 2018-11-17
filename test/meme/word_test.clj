(ns meme.word-test
  (:require [clojure.test :refer :all]
            [meme.word :refer :all]))

(deftest test-round-robin-words
  (testing "round-robin-wordsは"
    (testing "先頭の文字を取得してリストで返す"
      (is (= ["su"](round-robin-words ["switch" "user"] 1))))
    (testing "先頭の文字を取得してリストで返す"
      (is (= ["su" "sus" "swu" "swus"](round-robin-words ["switch" "user"] 2))))
    ))

(deftest test-takes
  (testing "takesは"
    (testing "プレフィックスのリストを返す"
      (is (= ["s" "sw"](takes 2 "switch"))))
    ))

(deftest test-include-words
  (testing "include-common-words"
    (testing "単語リストを含むものを返す。一文字のワードは除外"
      (is (= ["con" "cat" "c"] (include-common-words "concat" ["con" "cat" "x" "c"]))))
    ))

(deftest test-command-names
  (testing "command-namesは"
    (testing "コマンド名を返す"
      (is (= ["cat" "cc" "con"] (command-names ["cat" "concat"] 1 ["cat" "con"]))))
    ))

