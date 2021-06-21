(ns rf-todo-list.db
  (:require
    [ajax.core :refer [GET POST]]
    ))

(defn save [todo-list]
  (POST
   "http://localhost:3000/api/v1/todo-lists"
   {:headers {"Accept"       "application/transit+json"
              "Content-type" "application/edn"}
    :body    todo-list}
   ))

(defn get-all []
  (GET
    "http://localhost:3000/api/v1/todo-lists"
    {:headers {"Accept"       "application/json"
               "Content-type" "application/json"}
     :handler (fn [response]
                (js/alert response))
     :error-handler (fn [{:keys [status status-text]}]
                      (js/alert status))}
    ))