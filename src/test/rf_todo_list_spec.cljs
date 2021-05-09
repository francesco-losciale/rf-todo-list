(ns rf-todo-list-spec
  (:require [clojure.test :refer [deftest is testing]]
            [rf-todo-list.domain :refer [next-item-id]]
            [rf-todo-list.events :refer [todo-list-add-item-fx-handler]]))

(deftest next-item-id-test
  (is (= (next-item-id []) 1))
  (is (= (next-item-id [{:id 1}]) 2))
  (is (= (next-item-id [{:id 1} {:id 2}]) 3))
  (is (= (next-item-id [{:id 2} {:id 1}]) 3))
  )

(deftest todo-list-add-item-fx-handler-test
  (let [context {:coeffects {:next-item-id 1}
                 :db {:todo-list []}}]
   (is (= (todo-list-add-item-fx-handler (:coeffects context) [:todo-list-add-item {:text "text"}])
          {:next-item-id 1 :db {:todo-list [{:id 1 :text "text"}]}}))
   ))