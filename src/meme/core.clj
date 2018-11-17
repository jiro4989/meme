(ns meme.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.tools.cli :refer [parse-opts]]
            [meme.weight :as weight]
            [meme.word :as word])
  (:gen-class))

(defn read-words
  "ファイルを読み込み、文字列を小文字に変換して重複を削除してベクタとして返す"
  [fp]
  (->> (str/split (slurp fp) #"\n")
       (map #(str/lower-case %))
       distinct))

(def cli-options
  ; 重み数値の空白詰め桁数
  [["-p" "--padding-size" "padding size"
    :default 3
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 %) "number is over 0"]]
   ; 数値とコマンド名の区切り文字
   ["-d" "--delimiter" "delimiter"
    :default \:]
   ; 重みを出力しない
   ["-n" "--none"]
   ["-h" "--help"]])

(defn usage
  [summary]
  (->> ["meme is naming tool like linux commands."
        ""
        "options:"
        summary]
       (str/join \newline)))

(defn format-line
  "オプションごとの出力を返す"
  [m options]
  (let [{:keys [none padding-size delimiter]} options
        weight (:weight m)
        n (:name m)]
    ; オプション数が増えたら出力が変わる可能性があるためcondを使用
    (cond
      none (format "%s" n)
      :else (format (str "%" padding-size "d" delimiter "%s") weight n))))

(defn -main
  [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (if (or (zero? (count arguments))
            (:help options))
      (println (usage summary))
      (let [words (str/split (first args) #"\s")
            common-words (read-words "resources/word.txt")
            round-words (word/round-robin-words words 2)]
        (doseq [m (->> (word/command-names words 2 common-words)
                       (filter #(< 1 (count %)))
                       (map #(weight/weight % common-words round-words))
                       (sort-by :name)
                       (sort-by :weight))]
          (println (format-line m options)))))))
