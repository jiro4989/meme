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
  (->> (-> fp io/resource slurp (str/split #"\r?\n"))
       (map #(str/lower-case %))
       distinct))

(defn format-line
  "オプションごとの出力を返す"
  [m options]
  (let [{:keys [none padding-size delimiter]} options
        weight (:weight m)
        n (:name m)] ; nameという組み込み関数が存在するので分配束縛していない
    ; オプション数が増えたら出力が変わる可能性があるためcondを使用
    (cond
      none (format "%s" n)
      :else (format (str "%" padding-size "d" delimiter "%s") weight n))))

(defn weighting-words
  "コマンド名候補リストに重みを付けてマップとして返す"
  [round-words common-words]
  (->> round-words
       (filter #(< 1 (count %)))
       (map #(weight/weight % common-words round-words))
       (sort-by :name)
       (sort-by :weight)))

(def cli-options
  ; 重み数値の空白詰め桁数
  [["-p" "--padding-size int" "padding size"
    :default 3
    :parse-fn #(Integer/parseInt %)
    :validate [pos? "number is over 0"]]
   ; 数値とコマンド名の区切り文字
   ["-d" "--delimiter str" "delimiter"
    :default \:]
   ["-r" "--round-prefix-chars-size int" "round prefix characters size"
    :default 2
    :parse-fn #(Integer/parseInt %)
    :validate [pos? "number is over 0"]]
   ; 重みを出力しない
   ["-n" "--none"]
   ["-h" "--help"]])

(defn usage
  [summary]
  (str/join \newline ["meme is naming tool like linux commands."
                      ""
                      "options:"
                      summary]))

(defn -main
  [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
      (or (zero? (count arguments))
          (:help options)) (println (usage summary))
      (seq errors) (println errors)
      :else (let [words (str/split (first arguments) #"\s")
                  common-words (read-words "resources/word.txt")
                  round-words (word/round-robin-words words (:round-prefix-chars-size options))]
              (doseq [m (weighting-words round-words common-words)]
                (println (format-line m options)))))))
