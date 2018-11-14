(ns meme.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn takes
  "文字列から文字列を指定数まで順に取り出して返す
  ex: (takes 2 \"xyz\")
  -> [\"x\" \"xy\"]"
  [n word]
  (loop [taked []
         x 1]
    (if (< n x)
      taked
      (recur (conj taked (str/join (take x word)))
             (inc x)))))

(defn get-round-robin-words
  "単語のリストの先頭の文字列を取得し、総当たりで組み合わせて返す"
  [words n]
  (->> words
       (map #(takes n %))
       (reduce #(for [x %1 y %2] (.concat x y)))))

(defn read-words
  "ファイルを読み込み、文字列を小文字に変換して重複を削除してベクタとして返す"
  [fp]
  (->> (str/split (slurp fp) #"\n")
       (map #(str/lower-case %))
       distinct))

(defn include-words
  "単語リストが対象文字列内に含まれていれば返す"
  [word words]
  (filter #(str/includes? word %) words))

(defn get-command-names
  [words n common-words]
  (->> (conj (get-round-robin-words words n)
             (map #(include-words % common-words) words))
       flatten
       distinct
       sort))

(defn weight
  [word common-words]
  {:weight 0 :word word})

(defn -main
  [& args]
  (let [words (str/split (first args) #"\s")]
    (->> (get-command-names words 2 (read-words "resources/words.txt"))
         (filter #(< 2 (count %)))
         println)))

