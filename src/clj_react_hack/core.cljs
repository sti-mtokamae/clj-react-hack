(ns clj-react-hack.core
  (:require [helix.core :refer [defnc $]]
            [helix.hooks :refer [use-state]]
            [helix.dom :as d]
            [uix.core :as uix :refer [defui]]
            [uix.dom]
            ["react-dom/client" :as rdom]))

;; ========================================
;; UIx コンポーネント（shadcn/ui スタイル対応）
;; ========================================

;; UIx Button コンポーネント（デバッグ版）
(defui UIxButton [props]
  (let [on-click (:onClick props)
        variant (:variant props)
        children (:children props)
        base-classes "inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50"
        variant-classes (case variant
                         "destructive" "bg-red-600 text-white hover:bg-red-700"
                         "outline" "border border-gray-300 bg-white text-gray-700 hover:bg-gray-50"
                         "secondary" "bg-gray-100 text-gray-900 hover:bg-gray-200"
                         "ghost" "text-gray-700 hover:bg-gray-100"
                         "link" "text-blue-600 underline-offset-4 hover:underline"
                         ;; default
                         "bg-blue-600 text-white hover:bg-blue-700")
        all-classes (str base-classes " " variant-classes " h-10 px-4 py-2 shadow")]
    ($ :button {:className all-classes :on-click on-click} children)))

;; UIx Card コンポーネント
(defui UIxCard [{:keys [title description footer children]}]
  ($ :div {:style {:border "1px solid #e5e7eb" :border-radius "8px" :background "white" :box-shadow "0 1px 3px 0 rgba(0, 0, 0, 0.1)" :overflow "hidden"}}
    ($ :div {:style {:padding "16px" :border-bottom "1px solid #f3f4f6"}}
      (when title
        ($ :h3 {:style {:margin "0 0 8px 0" :color "#1f2937" :font-weight "600" :font-size "16px"}} title))
      (when description
        ($ :p {:style {:margin "0" :color "#6b7280" :font-size "14px"}} description)))
    (when children
      ($ :div {:style {:padding "16px"}}
        children))
    (when footer
      ($ :div {:style {:padding "16px" :background "#f9fafb" :border-top "1px solid #f3f4f6"}}
        footer))))

;; ========================================
;; 🔬 実験セクション：複数 islands 間の state 管理
;; ========================================

;; Island A: Counter (Helix + ローカル state)
(defnc IslandA-Counter []
  (let [[count set-count] (use-state 0)]
    (d/div {:style {:border "2px solid #3b82f6" :padding "16px" :border-radius "8px" :margin-bottom "20px" :background "#eff6ff"}}
      (d/h3 {:style {:color "#1e40af" :margin-top "0"}} "🏝️ Island A: Counter (Helix)")
      (d/p {:style {:font-size "24px" :font-weight "bold" :color "#1e40af"}}
        "Count: " count)
      (d/p {:style {:color "#666" :font-size "12px"}} "ローカル state")
      (d/button {:onClick #(set-count inc)
                :style {:padding "8px 16px" :background "#3b82f6" :color "white" :border "none" :border-radius "4px" :cursor "pointer"}}
        "Increment"))))

;; Island B: Card (Helix)
(defnc IslandB-Card []
  (let [[card-text set-card-text] (use-state "初期コンテンツ")]
    (d/div {:style {:border "2px solid #8b5cf6" :padding "16px" :border-radius "8px" :margin-bottom "20px" :background "#f3e8ff"}}
      (d/h3 {:style {:color "#8b5cf6" :margin-top "0"}} "📇 Island B: Card (Helix)")
      (d/p {:style {:color "#666"}}
        "カード内容:")
      (d/p {:style {:font-size "14px" :font-weight "bold" :color "#8b5cf6"}}
        card-text)
      (d/button {:onClick #(set-card-text (str "Updated at " (.toLocaleTimeString (js/Date.))))
                :style {:padding "8px 16px" :background "#8b5cf6" :color "white" :border "none" :border-radius "4px" :cursor "pointer"}}
        "Update Card Content"))))

;; Island C: UIx 正式実装（複雑なコンポーネント構成、TailwindCSS 統合、複数子要素）
(defui IslandC-UIxCardTest []
  (let [[count set-count!] (uix/use-state 0)
        [card-content set-card-content!] (uix/use-state "初期カード内容")]
    ($ :div {:style {:border "2px solid #059669" :padding "16px" :border-radius "8px" :margin-bottom "20px" :background "#f0fdf4"}}
      ($ :h3 {:style {:color "#059669" :margin-top "0"}} "🎨 Island C: UIx defui（複雑な構成）")
      ($ :p {:style {:color "#666" :font-size "14px" :margin "8px 0"}} 
        "UIx defui + 複数子コンポーネント + state 管理統合デモ")
      
      ;; UIx Card 構造（inline、複数の子要素統合）
      ($ :div {:style {:border "1px solid #e5e7eb" :border-radius "8px" :background "white" :box-shadow "0 1px 3px 0 rgba(0, 0, 0, 0.1)" :overflow "hidden" :margin-top "12px"}}
        ;; Card Header
        ($ :div {:style {:padding "16px" :border-bottom "1px solid #f3f4f6"}}
          ($ :h4 {:style {:margin "0 0 8px 0" :color "#1f2937" :font-weight "600" :font-size "16px"}} 
            "🛠️ UIx Card デモ")
          ($ :p {:style {:margin "0" :color "#6b7280" :font-size "14px"}} 
            "UIx defui で複雑なコンポーネント構成をテスト"))
        
        ;; Card Body
        ($ :div {:style {:padding "16px"}}
          ($ :div {:style {:margin-bottom "16px"}}
            ($ :div {:style {:font-weight "600" :color "#1f2937" :margin-bottom "8px"}} 
              (str "🔢 カウンター: " count))
            ($ :div {:style {:color "#6b7280" :font-size "14px" :margin-bottom "12px"}}
              card-content)
            
            ;; UIx Button コンポーネント群（複数バリエーション）
            ;; デバッグ：インライン定義（UIxButton コンポーネント経由ではなく直接 button 要素）
            ($ :div {:style {:display "flex" :gap "8px" :flex-wrap "wrap" :margin-bottom "12px"}}
              ($ :button {:on-click #(set-count! inc)
                         :style {:padding "8px 16px" :background "#3b82f6" :color "white" :border "none" :border-radius "4px" :cursor "pointer" :font-weight "500"}}
                "➕ Increment")
              ($ :button {:on-click #(set-count! dec)
                         :style {:padding "8px 16px" :background "#8b5cf6" :color "white" :border "none" :border-radius "4px" :cursor "pointer" :font-weight "500"}}
                "➖ Decrement")
              ($ :button {:on-click #(set-count! (constantly 0))
                         :style {:padding "8px 16px" :background "#6b7280" :color "white" :border "none" :border-radius "4px" :cursor "pointer" :font-weight "500"}}
                "🔄 Reset"))
            
            ;; テキスト更新ボタン
            ($ :button 
              {:on-click #(set-card-content! (str "Updated: " (.toLocaleTimeString (js/Date.))))
               :style {:padding "8px 16px" :background "#059669" :color "white" :border "none" :border-radius "4px" :cursor "pointer" :font-weight "500" :margin-top "8px"}}
              "📝 Update Card Content")))
        
        ;; Card Footer
        ($ :div {:style {:padding "16px" :background "#f9fafb" :border-top "1px solid #f3f4f6" :font-size "12px" :color "#6b7280"}}
          "✅ UIx + TailwindCSS 統合テスト")))))


;; 複数 islands を表示するデモ
(defnc MultiIslandDemo []
  (d/div {:style {:margin-bottom "40px"}}
    (d/h2 {:style {:color "#333"}} "🔬 複数 Islands デモ")
    (d/p {:style {:color "#666" :line-height "1.6"}}
      "以下の 3 つの islands は独立した state を持っています。")
    ($ IslandA-Counter)
    ($ IslandB-Card)
    ($ IslandC-UIxCardTest)))

;; ========================================
;; Helix コンポーネント（既存、参考用）
;; ========================================

;; オリジナルの App コンポーネント（参考用に残す）
(defnc ShadcnButton [{:keys [variant size children onClick]}]
  (let [base-classes "inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50"
        variant-classes (case variant
                         "destructive" "bg-destructive text-destructive-foreground hover:bg-destructive/90"
                         "outline" "border border-input bg-background hover:bg-accent hover:text-accent-foreground"
                         "secondary" "bg-secondary text-secondary-foreground hover:bg-secondary/80"
                         "ghost" "hover:bg-accent hover:text-accent-foreground"
                         "link" "text-primary underline-offset-4 hover:underline"
                         ;; default
                         "bg-primary text-primary-foreground hover:bg-primary/90")
        size-classes (case size
                      "sm" "h-9 rounded-md px-3"
                      "lg" "h-11 rounded-md px-8"
                      "icon" "h-10 w-10"
                      ;; default
                      "h-10 px-4 py-2")
        all-classes (str base-classes " " variant-classes " " size-classes " shadow")]
    (d/button {:className all-classes :onClick onClick} children)))

;; TailwindCSSを使ったButtonコンポーネント
(defnc TailwindButton [{:keys [variant children onClick]}]
  (let [base-classes "inline-flex items-center justify-center rounded-md text-sm font-medium transition-colors focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-ring disabled:pointer-events-none disabled:opacity-50 px-4 py-2 m-1"
        variant-classes (case variant
                         "destructive" "bg-red-600 text-white shadow hover:bg-red-700"
                         "outline" "border border-gray-300 bg-white text-gray-700 shadow-sm hover:bg-gray-50"
                         "secondary" "bg-gray-100 text-gray-900 shadow-sm hover:bg-gray-200"
                         "ghost" "text-gray-700 hover:bg-gray-100"
                         ;; default
                         "bg-blue-600 text-white shadow hover:bg-blue-700")
        all-classes (str base-classes " " variant-classes)]
    (d/button {:className all-classes :onClick onClick} children)))

;; シンプルなButtonコンポーネント
(defnc SimpleButton [{:keys [variant children onClick]}]
  (let [base-style {:padding "8px 16px"
                    :border-radius "4px"
                    :border "none"
                    :cursor "pointer"
                    :font-weight "500"
                    :margin "4px"}
        variant-styles (case variant
                        "destructive" {:background-color "#dc2626"
                                      :color "white"}
                        "outline" {:background-color "transparent"
                                  :color "#374151"
                                  :border "1px solid #d1d5db"}
                        "secondary" {:background-color "#f3f4f6"
                                    :color "#374151"}
                        "ghost" {:background-color "transparent"
                                :color "#374151"}
                        ;; default
                        {:background-color "#3b82f6"
                         :color "white"})
        style (merge base-style variant-styles)]
    (d/button {:style style :onClick onClick} children)))

(defnc App []
  (let [[count set-count] (use-state 0)]
    (d/div {:style {:padding "20px"}}
      (d/div {:style {:color "green" :font-size "24px" :margin-bottom "20px"}}
        "✅ Helix + React が正常に動作しています！")
      (d/div {:style {:color "blue" :margin-bottom "20px"}}
        "🎉 ReactとClojureScriptの統合成功！")
      
      ;; インタラクティブデモセクション
      (d/div {:style {:margin-bottom "30px" :padding "20px" :border "2px solid #3b82f6" :border-radius "8px" :background-color "#f8fafc"}}
        (d/h3 {:style {:color "#1e40af" :margin-bottom "15px" :font-size "18px"}}
          "🚀 インタラクティブデモ - カウンター")
        (d/div {:style {:margin-bottom "15px" :font-size "20px" :font-weight "bold"}}
          (str "現在のカウント: " count))
        (d/div {:style {:display "flex" :gap "8px" :flex-wrap "wrap"}}
          ($ ShadcnButton {:variant "default" :onClick #(set-count inc)} "➕ 増加")
          ($ ShadcnButton {:variant "destructive" :onClick #(set-count dec)} "➖ 減少")
          ($ ShadcnButton {:variant "outline" :onClick #(set-count (constantly 0))} "🔄 リセット")
          ($ ShadcnButton {:variant "secondary" :onClick #(set-count (constantly (rand-int 100)))} "🎲 ランダム")))
      
      (d/div {:style {:margin-bottom "20px"}}
        (d/h3 {:style {:color "#333" :margin-bottom "10px"}}
          "Shadcn/ui風 Buttonテスト:")
        (d/div {:style {:display "flex" :gap "8px" :flex-wrap "wrap" :margin-bottom "10px"}}
          ($ ShadcnButton {:variant "default"} "Default")
          ($ ShadcnButton {:variant "destructive"} "Destructive")
          ($ ShadcnButton {:variant "outline"} "Outline")  
          ($ ShadcnButton {:variant "secondary"} "Secondary")
          ($ ShadcnButton {:variant "ghost"} "Ghost")
          ($ ShadcnButton {:variant "link"} "Link"))
        (d/div {:style {:display "flex" :gap "8px" :flex-wrap "wrap"}}
          ($ ShadcnButton {:size "sm"} "Small")
          ($ ShadcnButton {:size "default"} "Default Size")
          ($ ShadcnButton {:size "lg"} "Large")))
      
      (d/div {:style {:margin-bottom "20px"}}
        (d/h3 {:style {:color "#333" :margin-bottom "10px"}}
          "TailwindCSS Buttonテスト:")
        (d/div {:style {:display "flex" :gap "8px" :flex-wrap "wrap"}}
          ($ TailwindButton {:variant "default"} "Default Button")
          ($ TailwindButton {:variant "destructive"} "Destructive")
          ($ TailwindButton {:variant "outline"} "Outline")  
          ($ TailwindButton {:variant "secondary"} "Secondary")
          ($ TailwindButton {:variant "ghost"} "Ghost")))
      
      (d/div {:style {:margin-bottom "20px"}}
        (d/h3 {:style {:color "#333" :margin-bottom "10px"}}
          "シンプルButtonテスト:")
        (d/div {:style {:display "flex" :gap "8px"}}
          ($ SimpleButton {:variant "default"} "Default Button")
          ($ SimpleButton {:variant "destructive"} "Destructive")
          ($ SimpleButton {:variant "outline"} "Outline")
          ($ SimpleButton {:variant "secondary"} "Secondary")
          ($ SimpleButton {:variant "ghost"} "Ghost"))))))

(defnc MainContent []
  (d/div {}
    ($ MultiIslandDemo)
    ($ App)))

(defonce root-a (atom nil))
(defonce root-b (atom nil))
(defonce root-c (atom nil))

(defn render-app []
  ;; Main content: Islands demo + App demo on #app
  (let [app-el (.getElementById js/document "app")]
    (when app-el
      (if @root-a
        (.render @root-a ($ MainContent))
        (let [new-root (rdom/createRoot app-el)]
          (reset! root-a new-root)
          (.render new-root ($ MainContent)))))))

(defonce _started (render-app))