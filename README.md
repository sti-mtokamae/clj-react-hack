# 🚀 clj-react-hack (Helix ✕ UIx Hybrid)

[![License](https://img.shields.io/badge/license-ISC-blue.svg)](LICENSE)
[![ClojureScript](https://img.shields.io/badge/ClojureScript-latest-5881d8.svg?logo=clojure&logoColor=white)](https://clojurescript.org/)
[![React](https://img.shields.io/badge/React-19.1.1-61dafb.svg?logo=react&logoColor=white)](https://reactjs.org/)
[![TailwindCSS](https://img.shields.io/badge/TailwindCSS-4.1.11-38bdf8.svg?logo=tailwind-css&logoColor=white)](https://tailwindcss.com/)
[![Helix](https://img.shields.io/badge/Helix-0.2.0-brightgreen.svg)](https://github.com/lilactown/helix)
[![UIx](https://img.shields.io/badge/UIx-1.4.5-32a852.svg)](https://github.com/pitch-io/uix)
[![Shadow-CLJS](https://img.shields.io/badge/Shadow--CLJS-3.1.8-success.svg)](https://shadow-cljs.github.io/docs/UsersGuide.html)

> **Modern ClojureScript + React 19 Development Environment with Shadcn/ui Design System**  
> モダンウェブアプリケーション開発のための完全統合環境

## ✨ 特徴

### 🎯 **技術スタック完全統合**
- ✅ **React 19** - 最新のReactバージョンwith Concurrent Features
- ✅ **ClojureScript** - 関数型プログラミングでフロントエンド開発
- ✅ **Helix** - ClojureScript ↔ React のエレガントな統合
- ✅ **UIx** - Hiccup ベースの React コンポーネント定義（Helix との共存対応）
- ✅ **Shadow-CLJS** - 高速ビルドとホットリロード
- ✅ **TailwindCSS** - ユーティリティファーストCSS
- ✅ **Shadcn/ui風** - プロフェッショナルなデザインシステム

### 🧩 **豊富なコンポーネントライブラリ**
- **Button Components** - 6つのバリアント (`default`, `destructive`, `outline`, `secondary`, `ghost`, `link`)
- **Card System** - 完全なカードコンポーネント一式
- **Size Variants** - 4つのサイズオプション (`sm`, `default`, `lg`, `icon`)
- **Utility Functions** - クラス名マージとスタイリング機能

### 🎮 **インタラクティブデモ**
- **🚀 ライブカウンターデモ** - 状態管理の実演
- **🎨 多段階ボタンスタイル** - 3つの異なるアプローチ
- **⚡ ホットリロード** - 瞬時の開発フィードバック
- **🎯 React Hooks統合** - `use-state`による状態管理

## 🛠️ 技術スタック

| 技術 | バージョン | 用途 | 特徴 |
|------|------------|------|------|
| **ClojureScript** | latest | 関数型プログラミング言語 | 不変データ構造、簡潔な構文 |
| **React** | 19.1.1 | UIフレームワーク | Concurrent Features、最新hooks |
| **Helix** | 0.2.0 | ClojureScript ↔ React統合 | 型安全、関数型React開発 |
| **UIx** | 1.4.5 | Hiccup ベース React コンポーネント | Helix との共存対応、defui マクロ |
| **TailwindCSS** | 4.1.11 | ユーティリティファーストCSS | 高速スタイリング、レスポンシブ |
| **Shadow-CLJS** | 3.1.8 | ClojureScriptビルドツール | 高速コンパイル、ホットリロード |
| **Class Variance Authority** | 0.7.1 | コンポーネントバリアント管理 | 型安全なスタイルバリアント |
| **Radix UI** | latest | アクセシブルUIプリミティブ | 標準準拠、カスタマイズ可能 |

## 📋 前提条件

開発環境に以下がインストールされている必要があります：

- **Node.js** v14以上
- **npm** または **yarn**
- **Java** 8以上（Shadow-CLJS用）

## 🚀 クイックスタート

### 1. リポジトリのクローン

```bash
git clone https://github.com/sti-mtokamae/clj-react-hack.git
cd clj-react-hack
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
clj-react-hack/
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

## 🎯 アーキテクチャ

### コンポーネント設計哲学

このプロジェクトは **3層コンポーネントアーキテクチャ** を実装しています：

1. **Simple Components** - インラインスタイルによる基本スタイリング
2. **TailwindCSS Components** - ユーティリティクラスベースのスタイリング  
3. **Shadcn/ui Components** - プロフェッショナルなデザインシステム

### 状態管理

**Helix hooks** を使用したローカル状態管理：

```clojure
(defnc Counter []
  (let [[count set-count] (use-state 0)]
    (d/div {}
      (d/p {} (str "Count: " count))
      ($ Button {:onClick #(set-count inc)} "Increment"))))
```

## 🔬 UIx ハイブリッド実験（進行中）

### 実験目的

このセクションでは **Helix と UIx の混在アプローチ** を段階的に検証しています。

#### 背景
- 複数の React islands をシェアド atom で繋ぐ場合、コンポーネント記法の統一が有用か検証
- Hiccup ベース（UIx）と React 関数型（Helix）の共存は実用的か評価
- 最終的に `clj-star-bridge` + `clj-react-hack` の monorepo 統合の下準備

#### 仮説
1. UIx コンポーネント定義と Helix コンポーネント定義は共存可能
2. atom による状態共有は両方で機能する
3. 開発体験の差（ビルド・REPL・デバッグ）を定量化できる

### 実装フェーズ

| フェーズ | 対象 | 状態 | 目標 |
|---------|------|------|------|
| **Phase 1** | README + Memory | ✅ 完了 | 実験目的・進捗の記録、islands表現統一 |
| **Phase 2** | shadow-cljs.edn | ✅ 完了 | UIx 依存を追加 |
| **Phase 3** | UIx コンポーネント実装 | ✅ 完了 | Island C で defui + uix/use-state 実装 |
| **Phase 4** | 複数 islands 統合 | ✅ 完了 | Helix + UIx ハイブリッド構成で動作検証 |
| **Phase 5** | UI デモ復帰 | ✅ 完了 | shadcn/ui + TailwindCSS ボタンテスト復帰 |
| **Phase 6** | ドキュメント化 | 🔄 進行中 | 知見の集約・実装結果の記録 |

### 検証結果

✅ **UIx は Helix と共存可能**
- 同一アプリケーション内で両方のコンポーネント定義が機能
- `uix.dom/create-root` + `uix.dom/render-root` API を使用
- フラグメント記法は Helix の `($ :<> ...)` ではなく通常の div で対応

✅ **ホットリロード対応**
- shadow-cljs watch mode で両フレームワークをリアルタイムコンパイル
- ファイル変更が即座に反映

⚠️ **実装上の学び**
- UIx と Helix は namespace aliasing で共存（どちらも `$` を export）
- UIx の `defui` マクロは正式コンポーネント定義に必須
- 複数 roots の管理は atom ベースで容易に実装可能

### 記録項目

各フェーズで以下を記録：
- ✅ ビルド時間・エラーの質
- ✅ REPL hotreload の体験
- ✅ React DevTools での見え方
- ✅ 困ったこと・できたこと

### リンク
- **進捗追跡**: `/memories/session/uix-experiment.md`
- **ブランチ**: `experiment/helix-uix-hybrid`

## 🎨 コンポーネント例

### Buttonコンポーネント

```clojure
(ns your-app.core
  (:require [components.ui.button :refer [Button]]
            [helix.core :refer [defnc $]]))

(defnc MyComponent []
  ($ Button {:variant "default" :size "lg" :onClick #(js/alert "Clicked!")}
     "Click me!"))
```

### Cardコンポーネント

```clojure
(ns your-app.core
  (:require [components.ui.card :refer [Card CardHeader CardTitle CardContent]]
            [helix.core :refer [defnc $]]))

(defnc MyCard []
  ($ Card {}
     ($ CardHeader {}
        ($ CardTitle {} "Card Title"))
     ($ CardContent {}
        "This is the card content.")))
```

## 🌟 機能ショーケース

アプリケーションには以下のインタラクティブデモが含まれています：

- **🚀 インタラクティブカウンター** - 状態管理のデモンストレーション
- **🎨 ボタンバリアント** - 複数のスタイリングアプローチ
- **📦 コンポーネントライブラリ** - 再利用可能なUIコンポーネント
- **🎯 ホットリロード** - 瞬時の開発フィードバック

## 🔧 設定

### TailwindCSS設定

Shadcn/ui色彩システムとCSS変数を使用：

```javascript
// tailwind.config.js
module.exports = {
  content: ["./public/index.html", "./src/**/*.cljs"],
  theme: {
    extend: {
      colors: {
        border: "hsl(var(--border))",
        primary: {
          DEFAULT: "hsl(var(--primary))",
          foreground: "hsl(var(--primary-foreground))",
        },
        // ... 更多颜色
      },
    },
  },
}
```

### Shadow-CLJS設定

```clojure
;; shadow-cljs.edn
{:source-paths ["src"]
 :dependencies [[lilactown/helix "0.2.0"]]
 :builds
 {:app
  {:target :browser
   :output-dir "public/js"
   :modules {:main {:init-fn th-tools-ui-hack.core/render-app}}
   :devtools {:http-root "public" :http-port 3000}}}}
```

## 🤝 コントリビューション

1. リポジトリをフォーク
2. フィーチャーブランチを作成 (`git checkout -b feature/amazing-feature`)
3. 変更をコミット (`git commit -m 'Add amazing feature'`)
4. ブランチにプッシュ (`git push origin feature/amazing-feature`)
5. プルリクエストを開く

## 🧪 テスト

```bash
# テスト実行（テストファイルが追加された場合）
npx shadow-cljs compile test
```

## � 謝辞

- **[Helix](https://github.com/lilactown/helix)** - 優秀なClojureScript React ラッパー
- **[Shadcn/ui](https://ui.shadcn.com/)** - デザインシステムのインスピレーション
- **[TailwindCSS](https://tailwindcss.com/)** - ユーティリティファーストCSSフレームワーク
- **[Shadow-CLJS](https://shadow-cljs.github.io/)** - 優れたClojureScriptビルドツール

## �📝 参考資料

### 公式ドキュメント
- [ClojureScript公式ドキュメント](https://clojurescript.org/)
- [Shadow-CLJS ユーザーガイド](https://shadow-cljs.github.io/docs/UsersGuide.html)
- [Helix ドキュメント](https://github.com/lilactown/helix)
- [React 19 ドキュメント](https://react.dev/)
- [TailwindCSS ドキュメント](https://tailwindcss.com/docs)

### デザインシステム
- [Shadcn/ui](https://ui.shadcn.com/)
- [Radix UI](https://www.radix-ui.com/)
- [Class Variance Authority](https://cva.style/)

### 開発ツール
- [Shadow-CLJS](https://shadow-cljs.github.io/)
- [Figwheel](https://figwheel.org/)
- [ClojureScript REPL](https://clojurescript.org/guides/quick-start)

---

**Built with ❤️ using ClojureScript + React 19 + Helix + TailwindCSS**  
**❤️で作られました ClojureScript + React 19 + Helix + TailwindCSS**
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
