(ns components.ui.button
  (:require ["react" :as react]
            ["@radix-ui/react-slot" :refer [Slot]]
            ["class-variance-authority" :refer [cva]]
            [helix.core :refer [defnc $]]
            [lib.utils :refer [cn]]))

;; Button variants using class-variance-authority
(def button-variants
  (cva
   "inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-colors focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-ring disabled:pointer-events-none disabled:opacity-50 [&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0"
   #js {:variants #js {:variant #js {:default "bg-primary text-primary-foreground shadow hover:bg-primary/90"
                                     :destructive "bg-destructive text-destructive-foreground shadow-sm hover:bg-destructive/90"
                                     :outline "border border-input bg-background shadow-sm hover:bg-accent hover:text-accent-foreground"
                                     :secondary "bg-secondary text-secondary-foreground shadow-sm hover:bg-secondary/80"
                                     :ghost "hover:bg-accent hover:text-accent-foreground"
                                     :link "text-primary underline-offset-4 hover:underline"}
                       :size #js {:default "h-9 px-4 py-2"
                                  :sm "h-8 rounded-md px-3 text-xs"
                                  :lg "h-10 rounded-md px-8"
                                  :icon "h-9 w-9"}}
        :defaultVariants #js {:variant "default"
                              :size "default"}}))

(defnc Button
  "Shadcn/ui Button component implemented in ClojureScript with Helix"
  [{:keys [className variant size asChild children] :as props
    :or {variant "default" size "default" asChild false}}]
  (let [Comp (if asChild Slot "button")
        button-props (-> props
                         (dissoc :className :variant :size :asChild :children)
                         (assoc :className (cn (button-variants #js {:variant variant :size size}) className)))]
    ($ Comp button-props children)))
