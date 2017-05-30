(ns braid.client.gateway.forms.user-auth.subs
  (:require
    [re-frame.core :refer [reg-sub]]
    [braid.client.gateway.helpers :as helpers]))

(reg-sub
  ::user
  (fn [state _]
    (get-in state [:user-auth :user])))

(reg-sub
  ::user-auth-mode
  (fn [state _]
    (cond
      (nil? (get-in state [:user-auth])) :checking
      (get-in state [:user-auth :checking?]) :checking
      (get-in state [:user-auth :user]) :authed
      (get-in state [:user-auth :oauth-provider]) :oauth-in-progress
      :else (get-in state [:user-auth :mode]))))

(reg-sub
  ::oauth-provider
  (fn [state _]
    (get-in state [:user-auth :oauth-provider])))

(reg-sub
  ::error
  (fn [state _]
    (get-in state [:user-auth :error])))

(helpers/reg-form-subs :braid.client.gateway.forms.user-auth.subs :user-auth)
