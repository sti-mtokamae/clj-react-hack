(ns clj-react-hack.components.island-b.core
  (:require [helix.core :as helix :refer [defnc]]
            [helix.hooks :refer [use-state]]
            [helix.dom :as d]))

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