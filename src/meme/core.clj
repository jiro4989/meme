(ns meme.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn takes
  "文字列から指定数値まで順に取り出して返す
   ex: (takes 2 xyz)
       -> [x y]"
  [n word]
  (loop [taked []
         x 1]
    (if (< n x)
      taked
      (recur (conj taked (->> word
                              (take x)
                              str/join))
             (inc x)))))

(defn get-round-robin-words
  "単語のリストの先頭の文字列を取得し、総当たりで組み合わせて返す"
  [words n]
  (->> words
       (map #(takes n %))
       (reduce #(for [x %1 y %2] (.concat x y)))))

(defn read-words
  "ファイルを読み込み、文字列を小文字に変換して重複を削除してベクタとして返す。"
  [fp]
  (->> (str/split (slurp fp) #"\n")
       (map #(str/lower-case %))
       distinct))

(defn include-words
  "対象文字の中に単語リストのものが含まれているものを返す"
  [word words]
  (filter #(str/includes? word %) words))

(defn -main
  [& args]
  (println (include-words "concatenate" (read-words "resources/words.txt")))
  )

