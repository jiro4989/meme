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

(deftest test-weight-short-word
  (testing "weight-short-wordは"
    (testing "単語が2文字なら1"
      (is (= 1 (weight-short-word "xy"))))
    (testing "単語が3文字なら2"
      (is (= 2 (weight-short-word "cat"))))
    (testing "単語が4文字なら2"
      (is (= 2 (weight-short-word "xyzx"))))
    (testing "単語が5文字なら1"
      (is (= 1 (weight-short-word "xyzxy"))))
    (testing "文字数が2〜5以外なら0"
      (is (= 0 (weight-short-word "1"))))
    (testing "文字数が2〜5以外なら0"
      (is (= 0 (weight-short-word "123456"))))
    (testing "文字数が10より多いと-65535"
      (is (= -65535 (weight-short-word "12345678901"))))
    (testing "空文字なら0"
      (is (= 0 (weight-short-word ""))))
    (testing "空文字なら0"
      (is (= 0 (weight-short-word nil))))
    ))

(deftest test-weight
  (testing "weightは"
    (testing "汎用的な名前なら重みは大きい"
      (is (= {:weight 8 :word "cat"} (weight "cat" ["concat" "cat"]))))
    (testing ""
      (is (= {:weight 4 :word "aaa"} (weight "aaa" ["concat" "cat"]))))
    ))

