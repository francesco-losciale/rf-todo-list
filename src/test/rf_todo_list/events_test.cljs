(ns rf-todo-list.events-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [rf-todo-list.events :refer [add-item-fx-handler
                                         remove-item-db-handler]]))

;; TODO: whenever we add fields to items' data, do we need to change the assertions of the tests as well?

(deftest add-item-fx-handler-test
  (let [cofx-with-empty-todo-list {:coeffects {:next-item-id 1 :db {:todo-list []}}}
        cofx-with-singleton-todo-list {:coeffects {:next-item-id 2 :db {:todo-list [{:id 1 :text "previously added"}]}}}
        item {:text "some description"}
        event [:todo-list-add-item item]]
    (is
      (= (add-item-fx-handler (:coeffects cofx-with-empty-todo-list) event)
         {:next-item-id 1 :db {:todo-list [{:id 1 :text "some description"}]}}))
    (is
      (= (add-item-fx-handler (:coeffects cofx-with-singleton-todo-list) [:todo-list-add-item item])
         {:next-item-id 2 :db {:todo-list [{:id 1 :text "previously added"} {:id 2 :text "some description"}]}}))
    ))

(deftest remove-item-db-handler-test
  (is
    (= (remove-item-db-handler {:todo-list [{:id 1 :text "previously added"}]}
                               [:todo-list-remove-item 1])
       {:todo-list []}))
  (is
    (= (remove-item-db-handler {:todo-list [{:id 1 :text "previously added"} {:id 2 :text "another one"}]}
                               [:todo-list-remove-item 2])
       {:todo-list [{:id 1 :text "previously added"}]})))
