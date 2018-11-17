(ns meme.core-test
  (:require [clojure.test :refer :all]
            [meme.core :refer :all]))

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

(deftest test-contains-then-inc
  (testing "contains-then-incは"
    (testing "単語が一般用語に含まれるなら5を返す"
      (is (= 5 (contains-then-inc "cat" ["concat" "cat"]))))
    (testing "単語が一般用語に含まれないなら0を返す"
      (is (= 0 (contains-then-inc "cat" ["concat"]))))
    (testing "単語が一般用語に含まれないなら0を返す"
      (is (= 0 (contains-then-inc "cat" []))))
    ))

(deftest test-vowel-then-inc
  (testing "vowel-then-incは"
    (testing "母音が75%含まれるなら3"
      (is (= 3 (vowel-then-inc "aiueo"))))
    (testing "母音が50%含まれるなら2"
      (is (= 2 (vowel-then-inc "caaat"))))
    (testing "母音が25%含まれるなら1"
      (is (= 1 (vowel-then-inc "caat"))))
    (testing "母音が含まれなければ0"
      (is (= 0 (vowel-then-inc "ct"))))
    (testing "母音が含まれなければ0"
      (is (= 0 (vowel-then-inc ""))))
    (testing "母音が含まれなければ0"
      (is (= 0 (vowel-then-inc nil))))
    ))

(deftest test-short-consonant-then-inc
  (testing "short-consonant-then-incは"
    (testing "子音が1文字なら0"
      (is (= 0 (short-consonant-then-inc "x"))))
    (testing "子音が2文字なら2"
      (is (= 2 (short-consonant-then-inc "xy"))))
    (testing "子音が3文字なら1"
      (is (= 1 (short-consonant-then-inc "xyz"))))
    (testing "子音が4文字以上なら0"
      (is (= 0 (short-consonant-then-inc "xyzx"))))
    (testing "子音が4文字以上なら0"
      (is (= 0 (short-consonant-then-inc "xyzxy"))))
    (testing "空文字なら0"
      (is (= 0 (short-consonant-then-inc ""))))
    (testing "空文字なら0"
      (is (= 0 (short-consonant-then-inc nil))))
    (testing "母音が含まれていたら0"
      (is (= 0 (short-consonant-then-inc "xyaz"))))
    ))

; (deftest test-weight
;   (testing "weightは"
;     (testing "TODO"
;       (is (= 0 (weight "cat" ["concat" "cat"]))))
;     ))

(deftest test-command-names
  (testing "command-namesは"
    (testing "コマンド名を返す"
      (is (= ["cat" "cc" "con"] (command-names ["cat" "concat"] 1 ["cat" "con"]))))
    ))

