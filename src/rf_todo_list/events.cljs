(ns rf-todo-list.events
  (:require
    [re-frame.core :as re-frame]
    ))


(re-frame/reg-event-db
  :todo-list-load
  (fn [db _]
    (assoc db :todo-list [{:id 1 :description "Item 1"} {:id 2 :description "Item 2"}])))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    (re-frame/dispatch [:todo-list-load])
    ))

(re-frame/reg-event-db
  :todo-list-add-item
  (fn [db event]
    (let [item (second event)]
      (update db :todo-list conj item))))


