(ns meme.weight-test
  (:require [clojure.test :refer :all]
            [meme.weight :refer :all]))

(deftest test-weight-contain-word
  (testing "weight-contain-wordは"
    (testing "単語が一般用語に含まれるなら5を返す"
      (is (= 5 (weight-contain-word "cat" ["concat" "cat"]))))
    (testing "単語が一般用語に含まれないなら0を返す"
      (is (= 0 (weight-contain-word "cat" ["concat"]))))
    (testing "単語が一般用語に含まれないなら0を返す"
      (is (= 0 (weight-contain-word "cat" []))))
    ))

(deftest test-weight-vowel
  (testing "weight-vowelは"
    (testing "母音が75%含まれるなら3"
      (is (= 3 (weight-vowel "aiueo"))))
    (testing "母音が50%含まれるなら2"
      (is (= 2 (weight-vowel "caaat"))))
    (testing "母音が25%含まれるなら1"
      (is (= 1 (weight-vowel "caat"))))
    (testing "母音が含まれなければ0"
      (is (= 0 (weight-vowel "ct"))))
    (testing "母音が含まれなければ0"
      (is (= 0 (weight-vowel ""))))
    (testing "母音が含まれなければ0"
      (is (= 0 (weight-vowel nil))))
    ))

(deftest test-weight-short-consonant
  (testing "weight-short-consonantは"
    (testing "子音が1文字なら0"
      (is (= 0 (weight-short-consonant "x"))))
    (testing "子音が2文字なら2"
      (is (= 2 (weight-short-consonant "xy"))))
    (testing "子音が3文字なら1"
      (is (= 1 (weight-short-consonant "xyz"))))
    (testing "子音が4文字以上なら0"
      (is (= 0 (weight-short-consonant "xyzx"))))
    (testing "子音が4文字以上なら0"
      (is (= 0 (weight-short-consonant "xyzxy"))))
    (testing "空文字なら0"
      (is (= 0 (weight-short-consonant ""))))
    (testing "空文字なら0"
      (is (= 0 (weight-short-consonant nil))))
    (testing "母音が含まれていたら0"
      (is (= 0 (weight-short-consonant "xyaz"))))
    ))

; (deftest test-weight
;   (testing "weightは"
;     (testing "TODO"
;       (is (= 0 (weight "cat" ["concat" "cat"]))))
;     ))

