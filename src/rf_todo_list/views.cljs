(ns rf-todo-list.views
  (:require
    [re-frame.core :as re-frame]
    [rf-todo-list.subs :as subs]
    ))

(defn main-panel []
      (let [todo-list @(re-frame/subscribe [:todo-list-load])]
           [:div
            [:ul
             (for [item todo-list] ^{:key (:id item)}
                  [:li [item :description]])
             ]]))
