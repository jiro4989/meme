(ns meme.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [meme.weight :as w])
  (:gen-class))

(defn takes
  "文字列から文字列を指定数まで順に取り出して返す
  ex: (takes 2 \"xyz\")
  -> [\"x\" \"xy\"]"
  [n word]
  (loop [took []
         x 1]
    (if (< n x)
      took
      (recur (conj took (str/join (take x word)))
             (inc x)))))

(defn round-robin-words
  "単語のリストの先頭の文字列を取得し、総当たりで組み合わせて返す"
  [words n]
  (->> words
       (map #(takes n %))
       (reduce #(for [x %1 y %2] (str x y)))))

(defn read-words
  "ファイルを読み込み、文字列を小文字に変換して重複を削除してベクタとして返す"
  [fp]
  (->> (str/split (slurp fp) #"\n")
       (map #(str/lower-case %))
       distinct))

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

(defn -main
  [& args]
  (let [words (str/split (first args) #"\s")
        common-words (read-words "resources/words.txt")
        round-words (round-robin-words words 2)]
    (doseq [m (->> (command-names words 2 common-words)
                   (filter #(< 1 (count %)))
                   (map #(w/weight % common-words round-words))
                   (sort-by :name)
                   (sort-by :weight))]
      (println m))))
