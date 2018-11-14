# meme

A Clojure library designed to ... well, that part is up to you.

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

## 実装ロジック

- コマンドに使えそうな文字列を取得
- 重み付けをしてコマンド名とともに出力

### コマンド名の決定の方針

- 複数の単語の先頭の1文字、2文字を取得して連結して取得
- 複数単語内に一般の単語が存在したら取得

### 重み付け

1. 常用単語(英語)
1. 発音しやすい(母音を含む)
1. 子音のみでコマンド名が短い

## Usage

FIXME

## License

Copyright © 2018 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
