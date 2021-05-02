(ns rf-todo-list.views
  (:require
    [reagent.core :as re-agent]
    [re-frame.core :as re-frame]
    [rf-todo-list.subs :as subs]
    ))

(defn main-panel []
  (let [todo-list @(re-frame/subscribe [:todo-list-load])
        value (re-agent/atom nil)]
    [:div
     [:ul
      (for [item todo-list] ^{:key (:id item)}
                            [:li [item :description]])
      ]
     [:form
      [:input {:name    "description"
               :type    "text"
               :on-change #(reset! value (-> % .-target .-value))
               }]
      [:button
       {:type "button"
        :on-click
              (fn [_]
                (re-frame/dispatch [:todo-list-add-item
                                    {:id 3 :description @value}]))}
       "Add"]]]))
