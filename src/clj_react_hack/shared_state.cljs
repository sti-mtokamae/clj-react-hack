(ns clj-react-hack.shared-state)

;; グローバル共有状態（Island 間で同期する場合に使用）
(defonce app-state (atom {:message "Shared state initialized"}))