(ns th-tools-ui-hack.core
  (:require [helix.core :refer [defnc $]]
            [helix.hooks :refer [use-state]]
            [helix.dom :as d]
            [uix.core :as uix :refer [defui]]
            [uix.dom]
            ["react-dom/client" :as rdom]))

;; 🔬 実験セクション：複数 islands 間の atom 共有

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

;; Island C: UIx 正式実装 (defui + uix/use-state)
(defui IslandC-UIxCardTest []
  (let [[count set-count!] (uix/use-state 0)]
    ($ :div {:style {:border "2px solid #059669" :padding "16px" :border-radius "8px" :margin-bottom "20px" :background "#f0fdf4"}}
      ($ :h3 {:style {:color "#059669" :margin-top "0"}} "🎨 Island C: UIx defui")
      ($ :p {:style {:color "#666"}} "Official UIx component with defui + $")
      ($ :div {:style {:border "1px solid #10b981" :border-radius "6px" :padding "12px" :background "white" :margin-top "12px"}}
        ($ :p {:style {:color "#059669" :font-weight "bold" :margin "0 0 8px 0"}} "UIx Card")
        ($ :p {:style {:color "#666" :font-size "14px" :margin-bottom "8px"}}
          "This is UIx defui component")
        ($ :p {:style {:font-size "12px" :color "#059669" :margin "0"}}
          (str "Counter: " count)))
      ($ :button {:onClick #(set-count! inc)
                  :style {:padding "8px 16px" :background "#059669" :color "white" :border "none" :border-radius "4px" :cursor "pointer" :margin-top "12px"}}
        "Increment"))))

;; 複数 islands を表示するデモ
(defnc MultiIslandDemo []
  (d/div {:style {:margin-bottom "40px"}}
    (d/h2 {:style {:color "#333"}} "🔬 複数 Islands デモ")
    (d/p {:style {:color "#666" :line-height "1.6"}}
      "以下の 3 つの islands は独立した state を持っています。")
    ($ IslandA-Counter)
    ($ IslandB-Card)
    ($ IslandC-UIxCardTest)))

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
      
      ;; 複数 islands atom 共有実験セクション
      ($ MultiIslandDemo)
      
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

(defonce root-a (atom nil))
(defonce root-b (atom nil))
(defonce root-c (atom nil))

(defn render-app []
  ;; Island A: Helix Counter on #app
  (let [app-el (.getElementById js/document "app")]
    (when app-el
      (if @root-a
        (.render @root-a ($ IslandA-Counter))
        (let [new-root (rdom/createRoot app-el)]
          (reset! root-a new-root)
          (.render new-root ($ IslandA-Counter))))))
  
  ;; Island B: Helix Card on #island-b
  (let [island-b-el (.getElementById js/document "island-b")]
    (when island-b-el
      (if @root-b
        (.render @root-b ($ IslandB-Card))
        (let [new-root (rdom/createRoot island-b-el)]
          (reset! root-b new-root)
          (.render new-root ($ IslandB-Card))))))
  
  ;; Island C: UIx 正式 API で初期化
  (let [island-c-el (.getElementById js/document "island-c-uix")]
    (when island-c-el
      (if @root-c
        (uix.dom/render-root ($ IslandC-UIxCardTest) @root-c)
        (let [new-root (uix.dom/create-root island-c-el)]
          (reset! root-c new-root)
          (uix.dom/render-root ($ IslandC-UIxCardTest) new-root))))))

(defonce _started (render-app))