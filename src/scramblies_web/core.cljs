(ns ^:figwheel-hooks scramblies-web.core
  (:require
    [reagent.dom]
    [reagent.core :refer [atom]]
    [ajax.core :refer [GET]]))

(defn invoke-scramble! [params result]
  (GET "http://localhost:8080/scramble"
        {:params params
         :response-format :json
         :handler #(reset! result {:status :result
                                   :result (get % "result")})
         :error-handler #(reset! result {:status :error
                                         :error-message %})}))

(defn param-input [k params]
  (fn []
    [:p
     [:label {:for k} k]
     [:input
      {:id k
       :type "text"
       :value (k @params)
       :on-change #(swap! params assoc k (-> % .-target .-value))}]]))

(defn app []
  (let [scramble-params (atom {:str1 "" :str2 ""})
        result (atom nil)]
    (fn []
      [:div
       [(param-input :str1 scramble-params)]
       [(param-input :str2 scramble-params)]
       [:p>button
        {:disabled (= :loading (:status @result))
         :on-click (fn [_]
                     (reset! result {:status :loading})
                     (invoke-scramble! @scramble-params result))}
        "scramble?"]
       "Result: " (case (:status @result)
                    :result [:b (str (:result @result))]
                    :error [:i (str "Error: " (:error-message @result))]
                    :loading [:i "Awaiting response..."]
                    [:i "None"])])))

(defn mount []
  (reagent.dom/render [app] (js/document.getElementById "app")))

(defn ^:after-load re-render []
  (mount))

(defonce start-up (do (mount) true))
