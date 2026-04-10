(ns components.ui.card-uix
  (:require [uix.core :as uix]
            [lib.utils :refer [cn]]))

;; 🔬 UIx 版 Card コンポーネント（Hiccup形式）
;; Island B で Helix と並行比較用

#_{:clj-kondo/ignore [:unresolved-symbol]}
(def CardUix
  (uix/memo
   (fn [{:keys [className children] :as props}]
     (let [card-class (cn "rounded-xl border bg-card text-card-foreground shadow" className)
           final-props (-> props
                           (dissoc :className :children)
                           (assoc :class card-class))]
       [:div final-props children]))))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(def CardHeaderUix
  (uix/memo
   (fn [{:keys [className children] :as props}]
     (let [header-class (cn "flex flex-col space-y-1.5 p-6" className)
           final-props (-> props
                           (dissoc :className :children)
                           (assoc :class header-class))]
       [:div final-props children]))))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(def CardTitleUix
  (uix/memo
   (fn [{:keys [className children] :as props}]
     (let [title-class (cn "text-2xl font-semibold leading-none tracking-tight" className)
           final-props (-> props
                           (dissoc :className :children)
                           (assoc :class title-class))]
       [:h3 final-props children]))))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(def CardContentUix
  (uix/memo
   (fn [{:keys [className children] :as props}]
     (let [content-class (cn "p-6 pt-0" className)
           final-props (-> props
                           (dissoc :className :children)
                           (assoc :class content-class))]
       [:div final-props children]))))
