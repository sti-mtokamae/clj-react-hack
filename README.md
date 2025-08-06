# th-tools-ui-hack

ClojureScript + React 19 + TailwindCSS プロジェクト

## 概要

このプロジェクトは、ClojureScriptとReact 19、TailwindCSSを組み合わせたウェブアプリケーションです。Shadow-CLJSを使用してClojureScriptをJavaScriptにコンパイルし、モダンなウェブ開発ワークフローを提供します。

## 技術スタック

- **ClojureScript** - 関数型プログラミング言語
- **React 19** - ユーザーインターフェースライブラリ
- **TailwindCSS** - ユーティリティファーストCSSフレームワーク
- **Shadow-CLJS** - ClojureScriptビルドツール

## 前提条件

- Node.js (v14以上)
- npm または yarn

## セットアップ

1. 依存関係をインストール:
```bash
npm install
```

2. 開発サーバーを起動:
```bash
npx shadow-cljs watch app
```

3. TailwindCSSをwatchモードで起動:
```bash
./tailwindcss -i ./src/css/input.css -o ./public/output.css --watch
```

4. ブラウザで以下のURLにアクセス:
   - アプリケーション: http://localhost:3000
   - Shadow-CLJSダッシュボード: http://localhost:9630/dashboard

## プロジェクト構造

```
├── src/
│   ├── css/
│   │   └── input.css          # TailwindCSS入力ファイル
│   └── th_tools_ui_hack/
│       └── core.cljs          # メインアプリケーションファイル
├── public/
│   ├── index.html             # HTMLエントリーポイント
│   ├── output.css             # 生成されたCSS
│   └── js/                    # 生成されたJavaScript
├── package.json               # Node.js依存関係
├── shadow-cljs.edn           # Shadow-CLJS設定
└── tailwind.config.js        # TailwindCSS設定
```

## 開発

- ClojureScriptファイルは `src/th_tools_ui_hack/` ディレクトリにあります
- CSS変更は `src/css/input.css` で行います
- ホットリロードが有効になっているため、ファイルを保存すると自動的にブラウザが更新されます

## ビルド

本番用ビルドを作成するには:
```bash
npx shadow-cljs release app
```

## ライセンス

ISC
