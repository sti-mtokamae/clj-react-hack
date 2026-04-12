(ns clj-react-hack.components.island-a.core
  (:require [helix.core :as helix :refer [defnc]]
            [helix.hooks :refer [use-state]]
            [helix.dom :as d]))

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