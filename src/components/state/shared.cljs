(ns components.state.shared
  (:require [helix.hooks :as hooks]))

;; 🔬 実験：島嶼間を繋ぐ共有状態
;; atom + Helix hooks で、複数 React 島嶼間で状態を共有

(def app-state
  "グローバル atom で複数の島嶼間状態を共有
   - :counter: 共有カウンター
   - :card-content: Card に表示するコンテンツ"
  (atom {:counter 0
         :card-content "初期コンテンツ"}))

(defn use-app-state
  "カスタムフック：shared atom の値を取得
   各コンポーネントが独立して atom を監視"
  []
  (let [[state set-state] (hooks/use-state @app-state)]
    (hooks/use-effect
      (fn []
        (let [watch-key (gensym "watch")]
          (add-watch app-state watch-key
            (fn [_ _ _ new-val]
              (set-state new-val)))
          ;; cleanup
          (fn []
            (remove-watch app-state watch-key)))))
    [state]))

(defn update-counter
  "カウンターをインクリメント"
  []
  (swap! app-state update :counter inc))

(defn set-card-content
  "Card のコンテンツを更新"
  [content]
  (swap! app-state assoc :card-content content))

(defn get-current-state
  "現在の状態を取得（非リアクティブ）"
  []
  @app-state)
