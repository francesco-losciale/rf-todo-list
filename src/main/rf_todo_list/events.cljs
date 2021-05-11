(ns rf-todo-list.events
  (:require
    [re-frame.core :as re-frame]
    [rf-todo-list.domain :refer [next-item-id]]
    ))


(re-frame/reg-event-db
  :todo-list-load
  (fn [db _]
    (assoc db :todo-list [])))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    (re-frame/dispatch [:todo-list-load])
    ))

(def add-next-item-id
  (re-frame/->interceptor
    :id :next-item-id
    :before (fn [context]
              (let [db (:db (:coeffects context))
                    todo-list (:todo-list db)]
                (assoc-in context [:coeffects :next-item-id] (next-item-id todo-list))
                ))))

(def todo-list-add-item-fx-handler
  (fn [cofx event]
    (let [db (:db cofx)
          next-item-id (:next-item-id cofx)
          item (second event)
          new-db (update db :todo-list conj (assoc item :id next-item-id))]
      (assoc cofx :db new-db)
      )))

(re-frame/reg-event-fx
  :todo-list-add-item
  [add-next-item-id]
  todo-list-add-item-fx-handler)


