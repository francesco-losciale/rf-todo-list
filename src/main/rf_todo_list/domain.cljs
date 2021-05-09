(ns rf-todo-list.domain)


(defn next-item-id [todo-list]
  ((fnil inc 0) (:id (last (sort-by :id todo-list)))))