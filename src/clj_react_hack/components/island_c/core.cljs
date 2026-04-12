(ns clj-react-hack.components.island-c.core
  (:require [uix.core :as uix :refer [defui]]
            [uix.dom]))

;; UIx Button コンポーネント（TailwindCSS 統合版 - shadcn/ui 風）
(defui UIxButton [{:keys [variant on-click children]}]
  (let [base-classes "inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 h-10 px-4 py-2"
        variant-classes (case variant
                         "secondary" "bg-purple-600 text-white hover:bg-purple-700 shadow-md"
                         "ghost" "text-gray-700 hover:bg-gray-100"
                         "outline" "border border-gray-300 bg-white text-gray-700 hover:bg-gray-50 shadow-sm"
                         ;; default
                         "bg-blue-600 text-white hover:bg-blue-700 shadow-md")
        all-classes (str base-classes " " variant-classes)]
    (uix/$ :button {:className all-classes :on-click on-click} children)))

;; Island C: UIx 正式実装（複雑なコンポーネント構成、TailwindCSS 統合、複数子要素）
(defui IslandC-UIxCardTest []
  (let [[count set-count!] (uix/use-state 0)
        [card-content set-card-content!] (uix/use-state "初期カード内容")]
    (uix/$ :div {:style {:border "2px solid #059669" :padding "16px" :border-radius "8px" :margin-bottom "20px" :background "#f0fdf4"}}
      (uix/$ :h3 {:style {:color "#059669" :margin-top "0"}} "🎨 Island C: UIx defui（複雑な構成）")
      (uix/$ :p {:style {:color "#666" :font-size "14px" :margin "8px 0"}} 
        "UIx defui + 複数子コンポーネント + state 管理統合デモ")
      
      ;; UIx Card 構造（inline、複数の子要素統合）
      (uix/$ :div {:style {:border "1px solid #e5e7eb" :border-radius "8px" :background "white" :box-shadow "0 1px 3px 0 rgba(0, 0, 0, 0.1)" :overflow "hidden" :margin-top "12px"}}
        ;; Card Header
        (uix/$ :div {:style {:padding "16px" :border-bottom "1px solid #f3f4f6"}}
          (uix/$ :h4 {:style {:margin "0 0 8px 0" :color "#1f2937" :font-weight "600" :font-size "16px"}} 
            "🛠️ UIx Card デモ")
          (uix/$ :p {:style {:margin "0" :color "#6b7280" :font-size "14px"}} 
            "UIx defui で複雑なコンポーネント構成をテスト"))
        
        ;; Card Body
        (uix/$ :div {:style {:padding "16px"}}
          (uix/$ :div {:style {:margin-bottom "16px"}}
            (uix/$ :div {:style {:font-weight "600" :color "#1f2937" :margin-bottom "8px"}} 
              (str "🔢 カウンター: " count))
            (uix/$ :div {:style {:color "#6b7280" :font-size "14px" :margin-bottom "12px"}}
              card-content)
            
            ;; UIx Button コンポーネント群（複数バリエーション）
            (uix/$ :div {:style {:display "flex" :gap "8px" :flex-wrap "wrap" :margin-bottom "12px"}}
              (uix/$ UIxButton {:variant "default" :on-click #(set-count! inc)} "➕ Increment")
              (uix/$ UIxButton {:variant "secondary" :on-click #(set-count! dec)} "➖ Decrement")
              (uix/$ UIxButton {:variant "ghost" :on-click #(set-count! (constantly 0))} "🔄 Reset"))
            
            ;; テキスト更新ボタン
            (uix/$ UIxButton 
              {:variant "outline" 
               :on-click #(set-card-content! (str "Updated: " (.toLocaleTimeString (js/Date.))))}
              "📝 Update Card Content")))
        
        ;; Card Footer
        (uix/$ :div {:style {:padding "16px" :background "#f9fafb" :border-top "1px solid #f3f4f6" :font-size "12px" :color "#6b7280"}}
          "✅ UIx + TailwindCSS 統合テスト")))))