(ns rf-todo-list.events
  (:require
    [re-frame.core :as re-frame]
    [rf-todo-list.domain :refer [next-item-id]]
    [rf-todo-list.db :as db]
    ))

(re-frame/reg-event-db
  :list-of-todo-list-load
  (fn [db _]
    (db/get-all (fn [response]
                  (re-frame/dispatch-sync
                    [:list-of-todo-list-set response])))
    db
    ))

(re-frame/reg-event-db
  :list-of-todo-list-set
  (fn [db event]
    (let [list-of-list (second event)]
      (assoc db :list-of-todo-list list-of-list)
      )))

(re-frame/reg-event-db
  :todo-list-load
  (fn [db event]
    (let [todo-list-id (second event)]
      (db/get-todo-list
        todo-list-id
        (fn [response]
          (re-frame/dispatch-sync
            [:todo-list-set (response :todo-list) todo-list-id])
          ))
      db)))

(re-frame/reg-event-db
  :todo-list-set
  (fn [db [_ todo-list todo-list-id]]
    (-> db
        (assoc :todo-list todo-list)
        (assoc :todo-list-id todo-list-id))
    ))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    (re-frame/dispatch [:list-of-todo-list-load])
    ))

(def add-next-item-id
  (re-frame/->interceptor
    :id :next-item-id
    :before (fn [context]
              (let [db (:db (:coeffects context))
                    todo-list (:todo-list db)]
                (assoc-in context [:coeffects :next-item-id] (next-item-id todo-list))
                ))))

(def add-item-fx-handler
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
  add-item-fx-handler)

(def remove-item-db-handler
  (fn [db event]
    (let [item-id (second event)
          todo-list (:todo-list db)
          is-item-id? #(= (:id %) item-id)]
      (assoc db :todo-list (remove is-item-id? todo-list))
      )))

(re-frame/reg-event-db
  :todo-list-remove-item
  remove-item-db-handler)

(re-frame/reg-event-db
  :todo-list-save
  (fn [db event]
    (let [todo-list (second event)
          todo-list-id (db :todo-list-id)]
      (if (nil? todo-list-id)
        (db/save todo-list
                 (fn [_]
                   (re-frame/dispatch [:list-of-todo-list-load])))
        (js/alert "Implement PUT")
        )
      (-> db
          (assoc :todo-list [])
          (assoc :todo-list-id nil))
      )))