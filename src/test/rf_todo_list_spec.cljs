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
  (let [context-with-empty-todo-list {:coeffects {:next-item-id 1 :db {:todo-list []}}}
        context-with-singleton-todo-list {:coeffects {:next-item-id 2 :db {:todo-list [{:id 1 :text "previously added"}]}}}
        item {:text "some description"}
        event [:todo-list-add-item item]]
   (is
     (= (todo-list-add-item-fx-handler (:coeffects context-with-empty-todo-list) event)
          {:next-item-id 1 :db {:todo-list [{:id 1 :text "some description"}]}}))
   (is
     (= (todo-list-add-item-fx-handler (:coeffects context-with-singleton-todo-list) [:todo-list-add-item item])
          {:next-item-id 2 :db {:todo-list [{:id 1 :text "previously added"} {:id 2 :text "some description"}]}}))
   )
  )