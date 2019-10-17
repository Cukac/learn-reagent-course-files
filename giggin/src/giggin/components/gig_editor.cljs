(ns giggin.components.gig-editor)

(defn form-group
      [{:keys [id type value values]}]
      [:div.form__group
       [:label.form__label {:for id} id]
       [:input.form__input {:type type
                            :id id
                            :value value
                            :on-change #(swap! values assoc (keyword id) (.. % -target -value))}]])

(defn gig-editor
      [modal values]
      [:div.modal (when @modal {:class "active"})
       [:div.modal__overlay]
       [:div.modal__container
        [:div.modal__body
         [form-group {:id "title"
                      :type "text"
                      :value (:title @values)
                      :values values}]
         [form-group {:id "desc"
                      :type "text"
                      :value (:desc @values)
                      :values values}]
         [form-group {:id "img"
                      :type "text"
                      :value (:img @values)
                      :values values}]
         [form-group {:id "price"
                      :type "number"
                      :value (:price @values)
                      :values values}]
         [:div.form__group
          [:label.form__label {:for "sold-out"} "sold out"]
          [:label.form__switch
           [:input {:type :checkbox
                    :checked (:sold-out @values)
                    :on-change #(swap! values assoc :sold-out (.. % -target -checked))}]
           [:i.form__icon]]]]
        [:div.modal__footer
         [:button.btn.btn--link.float--left
          {:on-click #(reset! modal false)}
          "Cancel"]
         [:button.btn.btn--secondary
          "Save"]]]])