(ns rf-todo-list.db
  (:require
    [ajax.core :refer [GET POST PUT]]
    ))

; TODO how to add env variable for backend api?

(defn save [todo-list handler]
  (POST
   "http://localhost:3000/api/v1/todo-lists"
   {:headers {"Accept"       "application/transit+json"
              "Content-type" "application/edn"}
    :body    todo-list
    :handler handler}
   ))

(defn update [id todo-list handler]
  (PUT
    (str "http://localhost:3000/api/v1/todo-lists/" id)
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

(defn get-todo-list [id handler]
  (GET
    (str "http://localhost:3000/api/v1/todo-lists/" id)
    {:headers {"Accept"       "application/transit+json"
               "Content-type" "application/edn"}
     :handler handler
     :error-handler (fn [{:keys [status status-text]}]
                      (js/alert status))}
    ))