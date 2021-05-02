(ns rf-todo-list.subs
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-sub
  :todo-list-load
  (fn [db]
    (:todo-list db)))