(ns rf-todo-list.events
  (:require
    [re-frame.core :as re-frame]
    ))


(re-frame/reg-event-db
  :todo-list-load
  (fn [db _]
    (assoc db :todo-list [{:id 1 :text "Item 1"} {:id 2 :text "Item 2"}])))

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
                    todo-list (:todo-list db)
                    new-item-id ((fnil inc 0) (:id (last todo-list)))]
                (assoc-in context [:coeffects :next-item-id] new-item-id)
                ))))

(re-frame/reg-event-fx
  :todo-list-add-item
  [add-next-item-id]
  (fn [cofx event]
    (let [db (:db cofx)
          next-item-id (:next-item-id cofx)
          item (second event)
          new-db (update db :todo-list conj item)]
      (assoc cofx :db new-db)
      )))


