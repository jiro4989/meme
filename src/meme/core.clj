(ns meme.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

; 母音
(def vowel "aiueo")

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

(defn round-robin-words
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

(defn include-common-words
  "単語リストが対象文字列内に含まれていれば返す"
  [word words]
  (filter #(str/includes? word %) words))

(defn command-names
  [words n common-words]
  (->> (conj (round-robin-words words n)
             (map #(include-common-words % common-words) words))
       flatten
       distinct
       sort))

(defn weight-contain-word
  [word common-words]
  (if (some #(= % word) common-words)
    5
    0))

(defn weight-vowel
  [word]
  (if (empty? word)
    0
    (let [c (->> word
                 (filter #(some (fn [x] (= x %)) vowel))
                 count)
          r (-> c 
                (/ (count word))
                float)]
      (cond
        (< 0.75 r) 3
        (< 0.5 r) 2
        (< 0.25 r) 1
        :else 0))))

(defn weight-short-consonant
  [word]
  (if (or (empty? word)
          (not (zero? (->> word
                           (filter #(some (fn [x] (= x %)) vowel))
                           count))))
    0
    (let [c (count word)]
      (cond
        (< c 2) 0
        (< c 3) 2
        (< c 4) 1
        (< c 5) 0
        :else 0))))

(defn weight
  [word common-words]
  (let [w (+ (weight-contain-word word common-words)
             (weight-vowel word)
             (weight-short-consonant word))]
    {:weight w :word word}))

(defn -main
  [& args]
  (let [words (str/split (first args) #"\s")]
    (->> (command-names words 2 (read-words "resources/words.txt"))
         (filter #(< 2 (count %)))
         println)))

