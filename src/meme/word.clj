(ns meme.word
  (:require [clojure.string :as str]))

(defn takes
  "文字列から文字列を指定数まで順に取り出して返す
  ex: (takes 2 \"xyz\")
  -> [\"x\" \"xy\"]"
  [n word]
  ; 単語の文字数以上を指定できないように下限値を設定
  (let [n2 (min n (count word))]
    (loop [took []
           x 1]
      (if (< n2 x)
        took
        (recur (conj took (str/join (take x word)))
               (inc x))))))

(defn round-robin-words
  "単語のリストの先頭の文字列を取得し、総当たりで組み合わせて返す"
  [words n]
  (->> words
       (map #(takes n %))
       (reduce #(for [x %1 y %2] (str x y)))))

(defn include-common-words
  "単語リストが対象文字列内に含まれていれば返す"
  [word words]
  (filter #(str/includes? word %) words))

(defn command-names
  "コマンド名を返す"
  [words n common-words]
  (->> (conj (round-robin-words words n)
             (map #(include-common-words % common-words) words))
       flatten
       distinct
       sort))

