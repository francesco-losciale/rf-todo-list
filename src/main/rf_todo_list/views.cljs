(ns rf-todo-list.views
  (:require
    [reagent.core :as re-agent]
    [re-frame.core :as re-frame]
    [rf-todo-list.subs]                                     ; call reframe subscription
    ))

(defn save-button [todo-list]
  [:button
   {:type "button"
    :on-click
          (fn [_]
            (re-frame/dispatch [:todo-list-save
                                {:todo-list todo-list}]))}
   "Save"])

(defn main-panel []
  (let [todo-list @(re-frame/subscribe [:todo-list-load :todo-list-save])]
    [:div
     [:ul
      (for [item todo-list] ^{:key (:id item)}
                            [:li
                             [:div
                              [item :text]
                              [:button
                               {:type "button"
                                :on-click
                                      (fn [_]
                                        (re-frame/dispatch
                                          [:todo-list-remove-item
                                           (:id item)]))}
                               " - "]]])
      ]
     (re-agent/with-let [draft (re-agent/atom nil)
                         value (re-agent/track #(or @draft ""))]
       [:div
        [:input {:type      "text"
                 :on-change #(reset! draft (.. % -target -value))
                 :value     @value
                 }]
        [:button
         {:type "button"
          :on-click
                (fn [_]
                  (if (not (nil? @value))
                    (re-frame/dispatch [:todo-list-add-item
                                        {:text @value}]))
                  (reset! draft ""))}
         "Add"]
        (save-button todo-list)
        ])]))
