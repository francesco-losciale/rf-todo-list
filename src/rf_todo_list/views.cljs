(ns rf-todo-list.views
  (:require
   [re-frame.core :as re-frame]
   [rf-todo-list.subs :as subs]
   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]
     ]))
