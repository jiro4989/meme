(ns meme.weight)

; 母音
(def vowel "aiueo")

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

