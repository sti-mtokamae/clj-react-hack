(ns lib.utils
  (:require ["clsx" :as clsx]
            ["tailwind-merge" :refer [twMerge]]))

(defn cn
  "Utility function to merge class names with tailwind-merge"
  [& inputs]
  (-> inputs
      clsx
      twMerge))
