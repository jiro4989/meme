# meme (mei mei)

![test](https://github.com/jiro4989/meme/workflows/test/badge.svg)

自作のCLIツールを命名するためのツール。

## 目的

自作のコマンドやツールを作ったときに名前を考えるのに苦労した。
Linuxのポピュラーなコマンドには名前の付け方にある程度規則性があるので、それを参
考に機械的に名前を算出できるようにしたいと考えた。

**重みの付け方はかなりガバガバなんであくまで参考値程度に...**

## 動作環境

Java 10

    openjdk version "10.0.2" 2018-07-17
    OpenJDK Runtime Environment (build 10.0.2+13-Ubuntu-1ubuntu0.18.04.3)
    OpenJDK 64-Bit Server VM (build 10.0.2+13-Ubuntu-1ubuntu0.18.04.3, mixed mode)

## 開発環境

- Clojure 1.9.0
- Leiningen 2.8.1
- Vim 8.0

## コマンドの例

| cmd   | origin word                      |
|-------|----------------------------------|
| su    | switch user                      |
| sudo  | switch user do                   |
| cp    | copy                             |
| mv    | move                             |
| rm    | remove                           |
| cat   | concatenate                      |
| grep  | global regular expression print  |
| sed   | stream editor                    |
| ln    | link                             |
| nl    | number line                      |
| cut   | cut                              |
| join  | join                             |
| ls    | list                             |
| paste | paste                            |

## Linuxのコマンドの命名種類

- 複数の言葉の先頭文字 (su, sudo, grep, sed)
- 特定の文字の一部 (cat)
- 子音のみ (cp, mv, rm, ls, ln)
- 元のワードそのまま (paste, join)

## 実装

### ロジック

- コマンドの由来となる複数の単語を入力
- 各コマンドから任意の数のプレフィックスを取得し、総当り組み合わせ
- 重み付けをしてコマンド名とともに出力

### 重み付け

1. 常用単語(英語)
1. 発音しやすい(母音を含む)
1. コマンド名がある程度短い

## 使い方

コマンドの基本的な使い方は以下の通り。

```bash
java -jar meme-1.0.0-SNAPSHOT-standalone.jar "global regular expression print"
  5:glreexpr
  5:glrepr
  5:glrexp
  5:glrexpr
  5:grexpr
  6:glreep
  6:glreepr
  6:glreexp
  6:glrep
  6:greepr
  6:greexp
  6:greexpr
  6:grepr
  6:grexp
  7:greep
  7:grep
```

ヘルプ

```bash
java -jar meme-1.0.0-SNAPSHOT-standalone.jar -h
```

    meme is naming tool like linux commands.

    options:
      -p, --padding-size int             3  padding size
      -d, --delimiter str                :  delimiter
      -r, --round-prefix-chars-size int  2  round prefix characters size
      -n, --none
      -h, --help

## License

Copyright © 2018 jiro4989
