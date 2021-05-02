(ns rf-todo-list.events
  (:require
   [re-frame.core :as re-frame]
   [rf-todo-list.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
