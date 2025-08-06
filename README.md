# th-tools-ui-hack

[![License](https://img.shields.io/badge/license-ISC-blue.svg)](LICENSE)
[![ClojureScript](https://img.shields.io/badge/ClojureScript-latest-green.svg)](https://clojurescript.org/)
[![React](https://img.shields.io/badge/React-19.1.1-blue.svg)](https://reactjs.org/)
[![TailwindCSS](https://img.shields.io/badge/TailwindCSS-4.1.11-blue.svg)](https://tailwindcss.com/)

> ClojureScript + React 19 + TailwindCSS を使用したモダンウェブアプリケーション開発のためのハックプロジェクト

## 🎯 プロジェクト概要

このプロジェクトは、ClojureScriptとReact 19、TailwindCSSを組み合わせたウェブアプリケーションの実験的プロジェクトです。Shadow-CLJSを使用してClojureScriptをJavaScriptにコンパイルし、モダンなウェブ開発ワークフローを提供します。

## 🛠️ 技術スタック

| 技術 | バージョン | 用途 |
|------|------------|------|
| **ClojureScript** | latest | 関数型プログラミング言語 |
| **React** | 19.1.1 | ユーザーインターフェースライブラリ |
| **TailwindCSS** | 4.1.11 | ユーティリティファーストCSSフレームワーク |
| **Shadow-CLJS** | 3.1.8 | ClojureScriptビルドツール |

## 📋 前提条件

開発環境に以下がインストールされている必要があります：

- **Node.js** v14以上
- **npm** または **yarn**
- **Java** 8以上（Shadow-CLJS用）

## 🚀 クイックスタート

### 1. リポジトリのクローン

```bash
git clone https://github.com/sti-pd-sandbox/th-tools-ui-hack.git
cd th-tools-ui-hack
```

### 2. 依存関係のインストール

```bash
npm install
```

### 3. 開発サーバーの起動

```bash
# Shadow-CLJS開発サーバーを起動
npx shadow-cljs watch app

# 別のターミナルでTailwindCSSをwatchモードで起動
./tailwindcss -i ./src/css/input.css -o ./public/output.css --watch
```

### 4. ブラウザでアクセス

- **アプリケーション**: http://localhost:3000
- **Shadow-CLJSダッシュボード**: http://localhost:9630/dashboard

## 📁 プロジェクト構造

## 📁 プロジェクト構造

```
th-tools-ui-hack/
├── 📁 src/
│   ├── 📁 css/
│   │   └── 📄 input.css          # TailwindCSS入力ファイル
│   └── 📁 th_tools_ui_hack/
│       └── 📄 core.cljs          # メインアプリケーションファイル
├── 📁 public/
│   ├── 📄 index.html             # HTMLエントリーポイント
│   ├── 📄 output.css             # 生成されたCSS（自動生成）
│   └── 📁 js/                    # 生成されたJavaScript（自動生成）
├── 📄 package.json               # Node.js依存関係
├── 📄 shadow-cljs.edn           # Shadow-CLJS設定
├── 📄 tailwind.config.js        # TailwindCSS設定
└── 📄 README.md                 # プロジェクト説明書
```

## 🔧 開発ガイド

### コード編集

- **ClojureScript**: `src/th_tools_ui_hack/` ディレクトリでコンポーネントとロジックを編集
- **スタイル**: `src/css/input.css` でカスタムCSSとTailwindディレクティブを編集
- **HTML**: `public/index.html` でベースHTMLテンプレートを編集

### ホットリロード

ファイルを保存すると、Shadow-CLJSとTailwindCSSが自動的に変更を検出し、ブラウザが更新されます。

### デバッグ

- **REPL**: Shadow-CLJSダッシュボード（http://localhost:9630/dashboard）でREPLに接続
- **ブラウザDevTools**: React Developer Toolsが利用可能

## 🏗️ ビルドとデプロイ

### 開発ビルド

```bash
npx shadow-cljs compile app
```

### 本番ビルド

```bash
# JavaScript最適化ビルド
npx shadow-cljs release app

# TailwindCSS本番ビルド
./tailwindcss -i ./src/css/input.css -o ./public/output.css --minify
```

## 🧪 テスト

```bash
# テスト実行（テストファイルが追加された場合）
npx shadow-cljs compile test
```

## 📝 参考資料

- [ClojureScript公式ドキュメント](https://clojurescript.org/)
- [Shadow-CLJS ユーザーガイド](https://shadow-cljs.github.io/docs/UsersGuide.html)
- [React 19 ドキュメント](https://react.dev/)
- [TailwindCSS ドキュメント](https://tailwindcss.com/docs)

## 🤝 コントリビューション

1. このリポジトリをフォーク
2. フィーチャーブランチを作成 (`git checkout -b feature/amazing-feature`)
3. 変更をコミット (`git commit -m 'Add some amazing feature'`)
4. ブランチにプッシュ (`git push origin feature/amazing-feature`)
5. プルリクエストを作成

## 📄 ライセンス

このプロジェクトは [ISC License](LICENSE) の下でライセンスされています。

## 🔗 関連プロジェクト

- [th-tools-dsl-gen](https://github.com/sti-pd-sandbox/th-tools-dsl-gen) - DSL生成ツール

---

**開発者**: [sti-pd-sandbox](https://github.com/sti-pd-sandbox)  
**プロジェクト開始**: 2025年8月
