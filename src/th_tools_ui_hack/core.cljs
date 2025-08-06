;; ClojureScript code for a React 19 application using Tailwind CSS
;; This file is part of the th-tools-ui-hack project.
(ns th-tools-ui-hack.core
  (:require ["react-dom/client" :as rdom]
            ["react" :as react]))

(defn Root []
  ;; React 19 style - using createElement more idiomatically
  (react/createElement "div"
                       #js {:className "bg-red-500 p-4 text-white text-2xl text-blue-600"}
                       "Hello, ClojureScript + React 19 + Tailwind!"))

(defn init []
  ;; React 19 - createRoot is the standard way (same as React 18+)
  (let [container (.getElementById js/document "app")
        root (.createRoot rdom container)]
    (.render root (react/createElement Root))))