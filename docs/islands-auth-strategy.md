# Islands アーキテクチャ：認証認可戦略

このドキュメントは、複数の独立した React Islands 環境における認証認可の実装パターンを解説しています。

## Islands 環境での認証管理戦略

**Islands 環境での認証管理戦略:**

| アプローチ | 方式 | メリット | デメリット |
|-----------|------|---------|-----------|
| **共有 Atom** | 全Islandが同一 atom 参照 | 単純、即座に同期 | 結合度が高い |
| **localStorage + Pub/Sub** | localStorage + イベント通知 | 疎結合、永続化可能 | 同期タイミング课題 |
| **バックエンド セッション** | サーバー側管理、API 認証 | 最も堅牢、CSRF対策可能 | サーバー負荷増加 |
| **JWT + LocalStorage** | JWT トークンを localStorage | ステートレス、スケーラブル | XSS リスク |
| **Context API + Pub/Sub** | React Context + イベント | React ネイティブ | 複数 root では複雑 |

## 推奨パターン（本プロジェクトへの適用例）

```clojure
;; 共有認証状態（atom）
(defonce auth-state (atom {:user nil :token nil :authenticated? false}))

;; 認証変更イベント（Island間通知）
(defonce auth-events (atom []))

;; Island A: ログインフォーム
(defnc IslandA-Login []
  (let [[email set-email] (use-state "")]
    (d/button {:onClick #(do
                          ;; バックエンド認証
                          (js/fetch "/api/login" {:method "POST" :body (js/JSON.stringify {:email @email})})
                          ;; 認証状態更新
                          (reset! auth-state {:user email :authenticated? true})
                          ;; イベント通知
                          (swap! auth-events conj {:type :login :user email}))}
      "Login")))

;; Island B: ユーザー情報表示
(defnc IslandB-UserProfile []
  (let [user (:user @auth-state)]
    (d/div {}
      (if user
        (d/p {} (str "Logged in as: " user))
        (d/p {} "Not logged in")))))

;; Island C: ログアウト
(defui IslandC-Logout []
  (let [authenticated? (:authenticated? @auth-state)]
    ($ :button {:onClick #(do
                           (reset! auth-state {:user nil :authenticated? false})
                           (swap! auth-events conj {:type :logout}))}
      (if authenticated? "Logout" "Log in first"))))
```

## 実装上の注意点

### 1. 認証 token の保安

- `localStorage` は XSS 脆弱性がある
- **HttpOnly Cookie** が理想的
- **バックエンド検証は必須**（クライアント側の state 改ざん防止）

### 2. Island間同期

- atom の変更は即座に全Islandに反映
- Pub/Sub イベントで確実な通知を保証
- 各Islandが atom の変更を watch-fn で監視可能

```clojure
;; Island が認証状態の変更を監視
(defnc IslandB-Monitor []
  (let [auth @auth-state]
    ;; 認証状態が変わると再レンダリング
    (d/div {} (str "Auth State: " auth))))
```

### 3. タイムアウト処理

- 各Islandが独立してタイムアウト判定しないこと
- 中央（atom）で管理
- 共有タイマーで全Islandに効果を及ぼす

```clojure
;; 共有タイムアウト管理
(defonce timeout-manager
  (let [timeout-atom (atom {:expires-at nil})]
    ;; グローバルタイマー
    (js/setInterval
      (fn []
        (when-let [expires (@timeout-atom :expires-at)]
          (when (> (js/Date.now) expires)
            (reset! auth-state {:user nil :authenticated? false})
            (reset! timeout-atom {:expires-at nil}))))
      1000)
    timeout-atom))
```

## 推奨戦略

> **推奨戦略**: **バックエンド session + 共有 atom（ローカルキャッシュ）** の組み合わせ。
> 
> - **サーバー側**: HttpOnly Cookie でセッション管理
> - **クライアント側**: atom でローカル認証状態をキャッシュ
> - **同期**: サーバーからの API 応答で atom を更新
> 
> この組み合わせで、認証の信頼性とパフォーマンスのバランスが最適です。

## 関連ドキュメント

- [README.md - Islands アーキテクチャ戦略](../README.md#islands-アーキテクチャ戦略)
- [UIx ハイブリッド実験](../README.md#uix-ハイブリッド実験進行中)
