# meme (mei mei)

自作のCLIツールを命名するためのツール。

## Usage

基本的な使い方は以下の通り。

```bash
java -jar meme-1.0.0-SNAPSHOT-standalone.jar "origin full name"
# ex: grep -> global regular expression print
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

## License

Copyright © 2018 jiro4989
