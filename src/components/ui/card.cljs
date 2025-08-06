(ns components.ui.card
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [lib.utils :refer [cn]]))

(defnc Card
  "Shadcn/ui Card component"
  [{:keys [className children] :as props}]
  (let [card-props (-> props
                       (dissoc :className :children)
                       (assoc :className (cn "rounded-xl border bg-card text-card-foreground shadow" className)))]
    (d/div card-props children)))

(defnc CardHeader
  "Card header component"
  [{:keys [className children] :as props}]
  (let [header-props (-> props
                         (dissoc :className :children)
                         (assoc :className (cn "flex flex-col space-y-1.5 p-6" className)))]
    (d/div header-props children)))

(defnc CardTitle
  "Card title component"
  [{:keys [className children] :as props}]
  (let [title-props (-> props
                        (dissoc :className :children)
                        (assoc :className (cn "text-2xl font-semibold leading-none tracking-tight" className)))]
    (d/h3 title-props children)))

(defnc CardDescription
  "Card description component"
  [{:keys [className children] :as props}]
  (let [description-props (-> props
                              (dissoc :className :children)
                              (assoc :className (cn "text-sm text-muted-foreground" className)))]
    (d/p description-props children)))

(defnc CardContent
  "Card content component"
  [{:keys [className children] :as props}]
  (let [content-props (-> props
                          (dissoc :className :children)
                          (assoc :className (cn "p-6 pt-0" className)))]
    (d/div content-props children)))

(defnc CardFooter
  "Card footer component"
  [{:keys [className children] :as props}]
  (let [footer-props (-> props
                         (dissoc :className :children)
                         (assoc :className (cn "flex items-center p-6 pt-0" className)))]
    (d/div footer-props children)))
