(ns rf-todo-list.subs
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-sub
  :todo-list-load
  (fn [db]
    (:todo-list db)))

(re-frame/reg-sub
  :todo-list-set
  (fn [db]
    (:todo-list db)))

(re-frame/reg-sub
  :list-of-todo-list-set
  (fn [db]
    (db :list-of-todo-list)
    ))