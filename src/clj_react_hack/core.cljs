(ns clj-react-hack.core
  (:require [helix.core :as helix]
            [uix.core :as uix :refer [defui]]
            [clj-react-hack.components.island-a.core :refer [IslandA-Counter]]
            [clj-react-hack.components.island-b.core :refer [IslandB-Card]]
            [clj-react-hack.components.island-c.core :refer [IslandC-UIxCardTest]]
            ["react-dom/client" :as rdom]))

;; ========================================
;; Main Layout: 複数 Islands を表示
;; ========================================

(defui MultiIslandDemo []
  (uix/$ :div {:style {:margin-bottom "40px"}}
    (uix/$ :h2 {:style {:color "#333"}} "🔬 複数 Islands デモ")
    (uix/$ :p {:style {:color "#666" :line-height "1.6"}}
      "以下の 3 つの islands は独立したファイルで定義されています（ファイル分割版）")
    (helix/$ IslandA-Counter)
    (helix/$ IslandB-Card)
    (uix/$ IslandC-UIxCardTest)))

(defui App []
  (uix/$ :div {:style {:padding "20px"}}
    (uix/$ :div {:style {:color "green" :font-size "24px" :margin-bottom "20px"}}
      "✅ Islands アーキテクチャ（ファイル分割版）")
    (uix/$ :div {:style {:color "blue" :margin-bottom "20px"}}
      "🎉 Helix と UIx が独立した Island として共存！")))

;; ========================================
;; UIx Button コンポーネント（デザインデモ用）
;; ========================================

;; Shadcn/ui風 Button
(defui ShadcnButton [{:keys [variant size children onClick]}]
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
    (uix/$ :button {:className all-classes :onClick onClick} children)))

;; TailwindCSS Button
(defui TailwindButton [{:keys [variant children onClick]}]
  (let [base-classes "inline-flex items-center justify-center rounded-md text-sm font-medium transition-colors focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-ring disabled:pointer-events-none disabled:opacity-50 px-4 py-2 m-1"
        variant-classes (case variant
                         "destructive" "bg-red-600 text-white shadow hover:bg-red-700"
                         "outline" "border border-gray-300 bg-white text-gray-700 shadow-sm hover:bg-gray-50"
                         "secondary" "bg-gray-100 text-gray-900 shadow-sm hover:bg-gray-200"
                         "ghost" "text-gray-700 hover:bg-gray-100"
                         ;; default
                         "bg-blue-600 text-white shadow hover:bg-blue-700")
        all-classes (str base-classes " " variant-classes)]
    (uix/$ :button {:className all-classes :onClick onClick} children)))

;; シンプル Button
(defui SimpleButton [{:keys [variant children onClick]}]
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
    (uix/$ :button {:style style :onClick onClick} children)))

;; ========================================
;; デザインデモレイアウト（UIx 版）
;; ========================================

(defui ButtonDemo []
  (let [[count set-count!] (uix/use-state 0)]
    (uix/$ :div {:style {:padding "20px"}}
      (uix/$ :div {:style {:margin-bottom "30px" :padding "20px" :border "2px solid #3b82f6" :border-radius "8px" :background-color "#f8fafc"}}
        (uix/$ :h3 {:style {:color "#1e40af" :margin-bottom "15px" :font-size "18px"}}
          "🚀 インタラクティブデモ - カウンター（UIx 版）")
        (uix/$ :div {:style {:margin-bottom "15px" :font-size "20px" :font-weight "bold"}}
          (str "現在のカウント: " count))
        (uix/$ :div {:style {:display "flex" :gap "8px" :flex-wrap "wrap"}}
          (uix/$ ShadcnButton {:variant "default" :onClick #(set-count! inc)} "➕ 増加")
          (uix/$ ShadcnButton {:variant "destructive" :onClick #(set-count! dec)} "➖ 減少")
          (uix/$ ShadcnButton {:variant "outline" :onClick #(set-count! (constantly 0))} "🔄 リセット")
          (uix/$ ShadcnButton {:variant "secondary" :onClick #(set-count! (constantly (rand-int 100)))} "🎲 ランダム")))
      
      (uix/$ :div {:style {:margin-bottom "20px"}}
        (uix/$ :h3 {:style {:color "#333" :margin-bottom "10px"}}
          "Shadcn/ui風 Buttonテスト（UIx 版）:")
        (uix/$ :div {:style {:display "flex" :gap "8px" :flex-wrap "wrap" :margin-bottom "10px"}}
          (uix/$ ShadcnButton {:variant "default"} "Default")
          (uix/$ ShadcnButton {:variant "destructive"} "Destructive")
          (uix/$ ShadcnButton {:variant "outline"} "Outline")  
          (uix/$ ShadcnButton {:variant "secondary"} "Secondary")
          (uix/$ ShadcnButton {:variant "ghost"} "Ghost")
          (uix/$ ShadcnButton {:variant "link"} "Link"))
        (uix/$ :div {:style {:display "flex" :gap "8px" :flex-wrap "wrap"}}
          (uix/$ ShadcnButton {:size "sm"} "Small")
          (uix/$ ShadcnButton {:size "default"} "Default Size")
          (uix/$ ShadcnButton {:size "lg"} "Large")))
      
      (uix/$ :div {:style {:margin-bottom "20px"}}
        (uix/$ :h3 {:style {:color "#333" :margin-bottom "10px"}}
          "TailwindCSS Buttonテスト（UIx 版）:")
        (uix/$ :div {:style {:display "flex" :gap "8px" :flex-wrap "wrap"}}
          (uix/$ TailwindButton {:variant "default"} "Default Button")
          (uix/$ TailwindButton {:variant "destructive"} "Destructive")
          (uix/$ TailwindButton {:variant "outline"} "Outline")  
          (uix/$ TailwindButton {:variant "secondary"} "Secondary")
          (uix/$ TailwindButton {:variant "ghost"} "Ghost")))
      
      (uix/$ :div {:style {:margin-bottom "20px"}}
        (uix/$ :h3 {:style {:color "#333" :margin-bottom "10px"}}
          "シンプルButtonテスト（UIx 版）:")
        (uix/$ :div {:style {:display "flex" :gap "8px"}}
          (uix/$ SimpleButton {:variant "default"} "Default Button")
          (uix/$ SimpleButton {:variant "destructive"} "Destructive")
          (uix/$ SimpleButton {:variant "outline"} "Outline")
          (uix/$ SimpleButton {:variant "secondary"} "Secondary")
          (uix/$ SimpleButton {:variant "ghost"} "Ghost"))))))

(defui MainContent []
  (uix/$ :div {}
    (uix/$ MultiIslandDemo)
    (uix/$ ButtonDemo)
    (uix/$ App)))

(defonce root-app (atom nil))

(defn render-app []
  ;; Main content: Islands demo + App demo on #app
  (let [app-el (.getElementById js/document "app")]
    (when app-el
      (if @root-app
        (.render @root-app (uix/$ MainContent))
        (let [new-root (rdom/createRoot app-el)]
          (reset! root-app new-root)
          (.render new-root (uix/$ MainContent)))))))

(defonce _started (render-app))