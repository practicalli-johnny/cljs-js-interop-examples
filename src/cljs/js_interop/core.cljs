(ns js-interop.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"}))

(defn root-component [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil (dom/h1 nil (:text app))))))

(om/root
 root-component
 app-state
 {:target (js/document.getElementById "app")})


;; ClojureScript defines special js namespace to allow accessing JavaScript types/functions/methods/objects defined in global scope (i.e. window object for browser).

;; (def text js/globalName)
;; JS output: namespace.text = globalName;

(def s "The octopus concoted colorized octane.")

(type s)
;;=> #object[String "function String() {
;;       [native code]
;;   }"]

(.toUpperCase s)

;; Property access
;; use .-propertyName to get the value of an objects property with that name. If you do not use the .- then ClojureScript will assume the property is bound to a function and call it.
(.-length s)

;; rather than write this out in full
(.toUpperCase (.toString (.charCodeAt s 7) 16))

;; use the double dot notation
(.. s (charCodeAt 7) (toString 16) toUpperCase)

(set! js/newVar 3)
js/newVar

(set! (.. js/document (getElementById "app") -innerHTML) "Hello, world!")
(set! (.-innerHTML (.getElementById js/document "app")) "Hello, world!!!")

(def arr (array 1 2 3))
(def obj (js-obj "x" 1 "y" 2))

(aget arr 1)
