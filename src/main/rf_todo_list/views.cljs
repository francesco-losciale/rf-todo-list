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

(defn add-button [value draft]
  [:button
   {:type "button"
    :on-click
          (fn [_]
            (if (not (nil? @value))
              (re-frame/dispatch [:todo-list-add-item
                                  {:text @value}]))
            (reset! draft ""))}
   "Add"])

(defn input-text [value draft]
  [:input {:type      "text"
           :on-change #(reset! draft (.. % -target -value))
           :value     @value
           }])

(defn todo-list-in-progress [todo-list]
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
                           " - "]]])])

(def draft (re-agent/atom nil))
(def value (re-agent/track #(or @draft "")))

(defn main-panel []
  (let [todo-list @(re-frame/subscribe [:todo-list-load :todo-list-save])]
    [:div
     (todo-list-in-progress todo-list)
     (input-text value draft)
     (add-button value draft)
     (save-button todo-list)]
    ))
