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

単体の demo page として動かせるだけでなく、`#app` を用意した既存 HTML や
server-rendered page に React root として埋め込めます。`clj-star-bridge` との
統合では、Clojure/Hiccup が生成する page shell の内側へ、この application
全体を 1 つの React root として mount します。

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
- **🏝️ Islands デモ** - Helix ✕ UIx ハイブリッド構成の実演
  - Island A: Counter (Helix) - use-state による状態管理
  - Island B: Card (Helix) - 複数コンポーネントの統合
  - Island C: UIx defui - Hiccup シンタックスの React 統合
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
npm ci
```

### 3. 開発サーバーの起動

```bash
# Shadow-CLJS開発サーバーを起動
npx shadow-cljs watch app

# 別のターミナルでTailwindCSSをwatchモードで起動
npx tailwindcss -i ./src/css/input.css -o ./public/output.css --watch
```

### 4. ブラウザでアクセス

- **アプリケーション**: http://localhost:3000
- **Shadow-CLJSダッシュボード**: http://localhost:9630/dashboard

## clj-star-bridge への組み込み

[`clj-star-bridge`](https://github.com/sti-mtokamae/clj-star-bridge) は
Clojure/Hiccup page shell、Datastar、SSE を担当し、このリポジトリは React
application bundle を提供します。

```text
Clojure/Hiccup shell
├── Datastar領域（通知、接続状態、サーバー主導UI）
└── #app
    └── clj-react-hack React root
```

`render-app` は page 内の `#app` を探し、存在する場合だけ `createRoot` で
`MainContent` を mount します。このため `public/index.html` から単体起動する場合と、
Hiccup page から bundle を読み込む場合で、ClojureScript entry pointを変更する
必要はありません。

DOM の所有権は分離します。

- React は `#app` の内側だけを更新する。
- Datastar は `#app` の内側を patch しない。
- Hiccup shell 側では React mount point を `data-ignore-morph` で保護する。
- 一斉通知など shell 共通の UI は React root の外側へ配置する。

最初の統合では、この demo application 全体を 1 つの React root として収容する
ことを確認済みです。これは既存 React SPA を直ちに分解するものではなく、既存の
routing、state、API client を保ったまま server-side shell を導入する移行入口です。
その後、必要性が確認できたページから Hiccup/Datastar とページ固有 React
component へ責任を分けます。

WSLc を使った2リポジトリの起動手順は、`clj-star-bridge` の
`containers/clj-dev/BUILD.md` を参照してください。

## 📁 プロジェクト構造

```
clj-react-hack/
├── 📁 src/
│   ├── 📁 css/
│   │   └── 📄 input.css          # TailwindCSS入力ファイル
│   └── 📁 clj_react_hack/
│       ├── 📄 core.cljs          # メインアプリケーション（Islands mount + Button デモ）
│       ├── 📄 shared_state.cljs  # 共有状態管理（atom）
│       └── 📁 components/        # コンポーネント分割ディレクトリ
│           ├── 📁 island_a/
│           │   └── 📄 core.cljs  # Island A: Counter (Helix)
│           ├── 📁 island_b/
│           │   └── 📄 core.cljs  # Island B: Card (Helix)
│           └── 📁 island_c/
│               └── 📄 core.cljs  # Island C: UIx Card Test
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

- **ClojureScript**: `src/clj_react_hack/` ディレクトリでコンポーネントとロジックを編集
- **スタイル**: `src/css/input.css` でカスタムCSSとTailwindディレクティブを編集
- **HTML**: `public/index.html` でベースHTMLテンプレートを編集

### ホットリロード

ファイルを保存すると、Shadow-CLJSとTailwindCSSが自動的に変更を検出し、ブラウザが更新されます。

### デバッグ

- **REPL**: Shadow-CLJSダッシュボード（http://localhost:9630/dashboard）でREPLに接続
- **ブラウザDevTools**: 標準のブラウザ開発者ツール（F12）で JavaScript、DOM を検査
- **React Developer Tools**: ブラウザ拡張（Chrome/Firefox）で React コンポーネント構造を可視化

#### React Developer Tools の使い方
1. **インストール**:
   - [Chrome拡張](https://chrome.google.com/webstore/detail/react-developer-tools)
   - [Firefox アドオン](https://addons.mozilla.org/firefox/addon/react-devtools/)

2. **DevTools を開く**:
   - F12 → 右上のタブを確認 → 「Components」タブを選択
   - またはコンソールで右クリック → `Inspect` を選択

3. **Component ツリーでの検査**:
   - コンポーネント階層を左パネルで閲覧
   - コンポーネント選択 → 右パネルで **Props** / **Hooks** / **State** を確認
   - 例：`IslandA-Counter` の `count` state の値がリアルタイム表示

4. **Profiler タブ**:
   - パフォーマンス計測
   - コンポーネントのレンダリング時間を分析
   - 不要な再レンダリング検出

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
npx tailwindcss -i ./src/css/input.css -o ./public/output.css --minify
```

## 🎯 アーキテクチャ

### スタイリング指定パターン

このプロジェクトでは、**3つのスタイリング指定パターン** を段階的に採用しています：

1. **Simple Components** - インラインスタイルによる基本スタイリング（最小限・高速）
2. **TailwindCSS Components** - ユーティリティクラスベースのスタイリング（保守性・効率化）
3. **Shadcn/ui Components** - プロフェッショナルなデザインシステム（統一性・アクセシビリティ）

各パターンは独立して使用でき、プロジェクトの進化に応じて段階的に採用できます。

### 状態管理

**Helix hooks** を使用したローカル状態管理：

```clojure
(defnc Counter []
  (let [[count set-count] (use-state 0)]
    (d/div {}
      (d/p {} (str "Count: " count))
      ($ Button {:onClick #(set-count inc)} "Increment"))))
```

**UIx hooks** を使用したローカル状態管理（同じく React hooks を活用）：

```clojure
(defui CounterUIx []
  (let [[count set-count!] (uix/use-state 0)]
    ($ :div {}
      ($ :p {} (str "Count: " count))
      ($ :button {:onClick #(set-count! inc)} "Increment"))))
```

> **注**: Helix と UIx は異なるマクロ（`defnc` vs `defui`）を使用しますが、**両者とも同じ React Hooks API** を活用します。`use-state` の参照方法が異なる点（`use-state` vs `uix/use-state`）に注意してください。

### Component 分割戦略

現在の実装は、`#app` に作成した **1つの React root** の下へ Island A・B・C を
component として配置しています。各 component は独立したローカル state を持ちますが、
複数 root や個別 bundle を使う厳密な Islands architecture ではありません。

**現在確認できていること:**
- Helix と UIx の component を同じ React root で混在できる
- 各 component が独自の state を管理できる（`use-state` / `uix/use-state`）
- component を別 namespace・別ファイルへ分割できる
- 必要に応じて Clojure atoms 経由で state を共有できる

**実装例:**
```clojure
;; 共有 state（atom を使用）
(defonce shared-state (atom {:count 0}))

;; Island A: Helix - ローカル state の独立管理
(defnc IslandA [] ...)

;; Island B: Helix - ローカル state の独立管理
(defnc IslandB [] ...)

;; Island C: UIx - ローカル state の独立管理
(defui IslandC [] ...)

;; 必要に応じて atom で state を共有
;; @shared-state で読み取り、reset! で更新
```

> **本プロジェクトの現状**: Island A・B・C は同じ React root の配下にあり、
> 各々独立した state を持っています。atom による共有は実装していません。

#### 将来、複数 root に分ける場合

将来、ページ内に複数の独立 React root を置く場合は、Hiccup 側で mount point を
複数出力し、component 名や初期 props を data 属性で渡します。その時点で初めて、
root 単位の mount、遅延読み込み、Shadow-CLJS module 分割の必要性を評価します。
現在の PoC では導入していません。

## 🔬 UIx ハイブリッド実験（進行中）

### 実験目的

このセクションでは **Helix と UIx の混在アプローチ** を段階的に検証しています。

#### 背景
- 同一 React root 内の複数 component を shared atom で繋ぐ場合、コンポーネント記法の統一が有用か検証
- Hiccup ベース（UIx）と React 関数型（Helix）の共存は実用的か評価
- `clj-star-bridge` からbundleを読み込むための統合境界を検証

#### 仮説
1. UIx コンポーネント定義と Helix コンポーネント定義は共存可能
2. atom による状態共有は両方で機能する
3. 開発体験の差（ビルド・REPL・デバッグ）を定量化できる

### 実装フェーズ

| フェーズ | 対象 | 状態 | 目標 |
|---------|------|------|------|
| **Phase 1** | README + Memory | ✅ 完了 | 実験目的・進捗の記録、component 表現統一 |
| **Phase 2** | shadow-cljs.edn | ✅ 完了 | UIx 依存を追加 |
| **Phase 3** | UIx コンポーネント実装 | ✅ 完了 | Island C で defui + uix/use-state 実装 |
| **Phase 4** | 複数 component 統合 | ✅ 完了 | Helix + UIx ハイブリッド構成で動作検証 |
| **Phase 5** | UI デモ復帰 | ✅ 完了 | shadcn/ui + TailwindCSS ボタンテスト復帰 |
| **Phase 6** | ドキュメント化・検証完全化 | ✅ 完了 | 知見の集約・実装結果の記録・UIx 検証完全化 |
| **Phase 7** | コンポーネント分割・alias 規範化 | ✅ 完了 | Island ファイル化、namespace alias 明示化、保守性向上 |

### 検証結果

✅ **UIx は Helix と共存可能**
- 同一application、同一React root内で両方のcomponent定義が機能
- `react-dom/client` の `createRoot` 配下で Helix と UIx component をrender
- フラグメント記法は Helix の `($ :<> ...)` ではなく通常の div で対応

✅ **ホットリロード対応**
- shadow-cljs watch mode で両フレームワークをリアルタイムコンパイル
- ファイル変更が即座に反映

✅ **UIx コンポーネント完全実装（Phase 6 検証）**
- **UIxButton コンポーネント**: shadcn/ui スタイル対応（6バリアント対応）
  - TailwindCSS クラス統合でスタイリング実装
  - size オプション対応（sm/default/lg/icon）
- **UIxCard コンポーネント**: 完全なカード構造実装
  - title・description・footer・children のサポート
  - 複雑なレイアウト対応（Card Header/Body/Footer）
- **Island C 拡張**: Helix 同等の複雑なコンポーネント構成
  - 複数子コンポーネント統合（UIxButton × 複数配置）
  - 複数 state 管理（count・card-content）
  - イベントハンドラ統合（onClick 処理）

✅ **実装上の学び**
- UIx と Helix は namespace aliasing で共存（どちらも `$` を export）
- UIx の `defui` マクロは正式コンポーネント定義に必須
- 複数 roots の管理は atom ベースで容易に実装可能
- **UIx でも Helix 同等レベルの複雑なコンポーネント構成が実装可能**（Shadow-CLJS compile 成功）

### 🔧 UIx × Helix 共存実装ガイド

#### Phase 7: コンポーネント分割・Namespace alias 規範化

**本プロジェクトの Phase 7 で実装された構造：**

```
src/clj_react_hack/
├── core.cljs                   ← メインアプリケーション（Island mount）
├── shared_state.cljs           ← 共有状態管理（atom）
└── components/
    ├── island_a/core.cljs      ← Helix 専用
    │   (:require [helix.core :as helix :refer [defnc]]
    │              [helix.dom :as d] ...)
    ├── island_b/core.cljs      ← Helix 専用
    │   (:require [helix.core :as helix :refer [defnc]] ...)
    └── island_c/core.cljs      ← UIx 専用
        (:require [uix.core :as uix :refer [defui]] ...)
```

**コンポーネント分割のメリット：**
- ✅ **物理的独立性**: 各 Island が独立したファイル（互いに呼び出さない）
- ✅ **Namespace 明示化**: `helix/$` / `uix/$` で framework を明示
- ✅ **保守性向上**: ファイル移動・拡張が容易
- ✅ **スケーラビリティ**: Island 追加時に既存コードへの影響なし
- ✅ **段階的移行**: IslandA・B は Helix 保持、IslandC は UIx、新規は UIx で統一可能

**実装上のポイント：**
```clojure
;;島_A (Helix 専用)
(ns clj-react-hack.components.island-a.core
  (:require [helix.core :as helix :refer [defnc]]
            [helix.dom :as d]))
(defnc IslandA []
  (helix/$ :div {} ...))  ; ← helix/$ で明示化

;; 島_C (UIx 専用)
(ns clj-react-hack.components.island-c.core
  (:require [uix.core :as uix :refer [defui]]))
(defui IslandC []
  (uix/$ :div {} ...))    ; ← uix/$ で明示化
```

**atom による state 共有（拡張可能）：**
```clojure
;; shared_state.cljs
(defonce app-state (atom {:message "初期状態"}))

;; 各 Island で watch して連携
;; (add-watch app-state :key (fn [k a old new] ...))
```

#### コンポーネント定義方式（Phase 7 完成版）

**UIx 統一版（core.cljs - メインアプリケーション）:**
```clojure
;; ✅ Phase 7 完成版：core.cljs は完全 UIx 統一化
(ns clj-react-hack.core
  (:require [helix.core :as helix]              ; ← IslandA・B 呼び出し用のみ
            [uix.core :as uix :refer [defui]]   ; ← メイン定義すべて UIx
            ...))

;; メインレイアウト：すべて defui（UIx）
(defui MultiIslandDemo []
  (uix/$ :div {}
    (uix/$ :h2 {} "複数 Islands デモ")
    (helix/$ IslandA-Counter)      ; ← Helix 島呼び出し：helix/$ で明示
    (helix/$ IslandB-Card)
    (uix/$ IslandC-UIxCardTest)    ; ← UIx 島呼び出し：uix/$ で明示
  ))

;; ボタンデモ：すべて defui（UIx）
(defui ButtonDemo []
  (let [[count set-count!] (uix/use-state 0)]
    (uix/$ :div {}
      (uix/$ ShadcnButton {:variant "default" :onClick #(set-count! inc)} "➕")
      (uix/$ TailwindButton {} "Tail")
      (uix/$ SimpleButton {} "Simple")
    )))

;; ボタンコンポーネント：すべて defui（UIx）
(defui ShadcnButton [{:keys [variant size children onClick]}] ...)
(defui TailwindButton [{:keys [variant children onClick]}] ...)
(defui SimpleButton [{:keys [variant children onClick]}] ...)
```

**Helix 島（parts/island_a/core.cljs）- Helix 専用保持：**
```clojure
;; ← Helix 専用：defnc + helix.dom
(ns clj-react-hack.components.island-a.core
  (:require [helix.core :as helix :refer [defnc]]
            [helix.dom :as d]))

(defnc IslandA-Counter []
  (let [[count set-count] (use-state 0)]
    (d/div {}
      (d/button {:onClick #(set-count inc)} "+"))))
```

**UIx 島（components/island_c/core.cljs）- UIx 専用：**
```clojure
;; ← UIx 専用：defui + uix/$
(ns clj-react-hack.components.island-c.core
  (:require [uix.core :as uix :refer [defui]]))

(defui IslandC-UIxCardTest []
  (let [[count set-count!] (uix/use-state 0)]
    (uix/$ :div {}
      (uix/$ :button {:onClick #(set-count! inc)} "+"))))
```

**重要ルール:**
- ✅ **`defui` 内は常に `uix/$`**: UIx コンポーネント定義内はすべて uix/$ で統一
- ✅ **異 framework コンポーネント参照も alias で明示**: `(helix/$ IslandA)` / `(uix/$ IslandC)`
- ✅ **core.cljs は UIx 主軸**: メインアプリケーションを UIx で統一
- ✅ **古い Helix 島は保持**: IslandA・B は現状維持、新規実装は UIx で統一可能

;; 参考：Helix での同等実装
(defnc IslandB-Helix []
  (let [[count set-count] (use-state 0)]
    ($ :div {}
      ($ Button {:onClick #(set-count inc)} "Increment"))))
```

#### TailwindCSS 統合（UIxButton）
```clojure
(defui UIxButton [{:keys [variant on-click children]}]
  (let [base-classes "inline-flex items-center..."
        variant-classes (case variant
                         "secondary" "bg-purple-600 hover:bg-purple-700"
                         "default" "bg-blue-600 hover:bg-blue-700")]
    (uix/$ :button 
      {:className (str base-classes " " variant-classes)
       :on-click on-click} 
      children)))
```

**重要：**
- props は destructuring で受け取る `[{:keys [...]}]` 形式
- children は自動的に props マップに含まれる
- `:on-click` は Hiccup では `:on-click`（`:onClick` ではない）
- className は TailwindCSS クラス文字列を使用

### 比較評価：Helix vs UIx

**能力的には同等：Helix でできることは UIx でも実現可能**

互いのシンタックスで同等のコンポーネント機能が実装可能：

| 側面 | Helix | UIx |
|------|-------|-----|
| シンタックス | 関数型、React ネイティブ | Hiccup ベース、宣言的 |
| state 管理 | use-state（React Hooks） | uix/use-state（同じく React Hooks） |
| コンポーネント定義 | defnc（マクロ） | defui（マクロ） |
| ホットリロード | ✅ | ✅ |
| 複数 root 対応 | ✅ | ✅ |
| Hiccup ネイティブ対応 | ✗ | ✅ |
| 複数子コンポーネント統合 | ✅（IslandB） | ✅（IslandC 検証済み） |
| TailwindCSS 統合 | ✅ | ✅（UIxButton/UIxCard） |
| 複雑な state 管理 | ✅（例：card-text） | ✅（例：count・card-content） |

**実装根拠（Phase 6 検証完了）**：
- Island B（Helix）と Island C（UIx）が同等レベルのコンポーネント機能を実装
- Island C で UIxButton・UIxCard を用いたシャドウ/ui 風 Component 統合確認
- Shadow-CLJS ビルド成功（2 compiled files）

> **UIx を制御主軸に選出の根拠**
> 
> 技術的には両者は等価ですが、Islands アーキテクチャの統一性と Clojure ベース（Hiccup）のコンポーネント記法を理由に、
> **UIx を制御主軸として採用する方針を決定**。
> Helix は例示・比較用に並行維持。
> 
> **目的：**
> - 📐 設計統一：全 Island を UIx defui で統一可能
> - 🔧 Clojure らしさ：Hiccup データ記述で関数型プログラミング体験向上
> - 🎯 検証完了：UIx が Helix 同等の機能・複雑性対応を実装検証により確認

### ⚠️ よくあるトラブルと解決方法（UIx 主軸）

| 問題 | 原因 | 解決方法 |
|------|------|--------|
| UIx コンポーネント内の props が undefined | UIx 内で Helix の `$` を使用してしまう | `defui` マクロ内では必ず `uix/$` を明示的に使用 |
| `:on-click` が null で渡される | $ マクロの混在（UIx 内で Helix $ を参照） | UIx コンポーネントは `uix/$` で完全統一 |
| TailwindCSS クラスが反映されない | `:className` 属性が文字列でない | className は文字列形式で指定（`(str base-classes " " variant-classes)`） |
| 子要素が React child として invalid | `children` をマップのままレンダリング | `children` は自動的に props に含まれるため、destructuring で正しく取得 |
| ホットリロード時に state がリセット | component 参照の変更 | `defui` マクロを正しく使用し、component 参照を安定化 |
| Helix コンポーネント内で UIx コンポーネントが動作しない | 異なる $ マクロで namespace 汚染 | Helix 内で UIx を使う場合も `uix/$` で明示的に指定 |

### 実装チェックリスト（UIx 主軸開発）

- [ ] `defui` で UIx コンポーネントを定義（新規コンポーネントはこれが基本）
- [ ] UIx コンポーネント内では **すべて `uix/$`** で統一
- [ ] props destructuring は `[{:keys [...]}]` 形式
- [ ] Hiccup イベントハンドラは `:on-click`（React の `:onClick` ではなく）
- [ ] TailwindCSS は className（文字列）で指定
- [ ] 複数 state は `(uix/use-state)` で宣言
- [ ] Helix コンポーネントを参照する場合は明示的に `$ HelixComponent` で指定

### 記録項目

各フェーズで以下を記録：
- ✅ ビルド時間・エラーの質
- ✅ REPL hotreload の体験
- ✅ React DevTools での見え方
- ✅ 困ったこと・できたこと

### リンク
- **進捗追跡**: `/memories/session/uix-experiment.md`
- **ブランチ**: `experiment/helix-uix-hybrid`
- **関連ドキュメント**: [Islands アーキテクチャ：認証認可戦略](docs/islands-auth-strategy.md)

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
 :dependencies [[lilactown/helix "0.2.0"]
               [com.pitch/uix.core "1.4.5"]
               [com.pitch/uix.dom "1.4.5"]]
 :builds
 {:app
  {:target :browser
   :output-dir "public/js"
   :asset-path "/js"
   :modules {:main {:init-fn clj-react-hack.core/render-app}}
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

## 💝 謝辞

- **[Helix](https://github.com/lilactown/helix)** - 優秀なClojureScript React ラッパー
- **[Shadcn/ui](https://ui.shadcn.com/)** - デザインシステムのインスピレーション
- **[TailwindCSS](https://tailwindcss.com/)** - ユーティリティファーストCSSフレームワーク
- **[Shadow-CLJS](https://shadow-cljs.github.io/)** - 優れたClojureScriptビルドツール

## 📚 参考資料

### 公式ドキュメント
- [ClojureScript公式ドキュメント](https://clojurescript.org/)
- [Shadow-CLJS ユーザーガイド](https://shadow-cljs.github.io/docs/UsersGuide.html)
- [Helix ドキュメント](https://github.com/lilactown/helix)
- [UIx ドキュメント](https://github.com/pitch-io/uix)
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

**Built with ❤️ using ClojureScript + React 19 + Helix + UIx + TailwindCSS**  
**❤️で作られました ClojureScript + React 19 + Helix + UIx + TailwindCSS**

## 📄 ライセンス

このプロジェクトは [ISC License](LICENSE) の下でライセンスされています。

## 🔗 関連プロジェクト

- [th-tools-dsl-gen](https://github.com/sti-pd-sandbox/th-tools-dsl-gen) - DSL生成ツール

---

**開発者**: [sti-mtokamae](https://github.com/sti-mtokamae)  
**プロジェクト開始**: 2026年4月
