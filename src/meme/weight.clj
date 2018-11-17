(ns meme.weight)

; 母音
(def vowel "aiueo")

(defn weight-contain-word
  "指定の単語が、単語リストに含まれる場合は重み値を返す"
  [word common-words]
  (if (some #(= % word) common-words)
    5
    0))

(defn weight-vowel
  "母音がコマンド名に含まれる割合に応じた重みを返す"
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
  "短い子音のみで構成されるかどうかで重みをつける"
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
  "重みを付与したマップとして返す"
  [word common-words]
  (let [w (+ (weight-contain-word word common-words)
             (weight-vowel word)
             (weight-short-consonant word))]
    {:weight w :word word}))
