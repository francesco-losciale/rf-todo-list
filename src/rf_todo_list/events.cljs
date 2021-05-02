(ns rf-todo-list.events
  (:require
   [re-frame.core :as re-frame]
   [rf-todo-list.db :as db]
   ))


(re-frame/reg-event-db
  :todo-list-load
  (fn [db _]
      (assoc db :todo-list [{:id 1 :description "Item 1" } {:id 2 :description "Item 2" }])))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
     (re-frame/dispatch [:todo-list-load])
     ))
