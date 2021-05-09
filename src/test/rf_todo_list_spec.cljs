(ns rf-todo-list-spec
  (:require [clojure.test :refer [deftest is testing]]
            [rf-todo-list.domain :refer [next-item-id]]))

(deftest next-item-id-test
  (is (= (next-item-id []) 1))
  (is (= (next-item-id [{:id 1}]) 2))
  (is (= (next-item-id [{:id 1} {:id 2}]) 3))
  (is (= (next-item-id [{:id 2} {:id 1}]) 3))
  )