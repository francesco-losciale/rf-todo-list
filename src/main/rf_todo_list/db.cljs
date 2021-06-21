(ns rf-todo-list.db
  (:require
    [ajax.core :refer [GET POST]]
    ))

(defn save [todo-list handler]
  (POST
   "http://localhost:3000/api/v1/todo-lists"
   {:headers {"Accept"       "application/transit+json"
              "Content-type" "application/edn"}
    :body    todo-list
    :handler handler}
   ))

(defn get-all [handler]
  (GET
    "http://localhost:3000/api/v1/todo-lists"
    {:headers {"Accept"       "application/transit+json"
               "Content-type" "application/edn"}
     :handler handler
     :error-handler (fn [{:keys [status status-text]}]
                      (js/alert status))}
    ))