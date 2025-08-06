(ns th-tools-ui-hack.core
  (:require [helix.core :refer [defnc $]]
            [helix.hooks :refer [use-state]]
            [helix.dom :as d]
            ["react-dom/client" :as rdom]))

;; Shadcn/ui風のButtonコンポーネント（シンプル版）
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

(defn init []
  (let [root-el (.getElementById js/document "app")
        root (rdom/createRoot root-el)]
    (.render root ($ App))))

(init)