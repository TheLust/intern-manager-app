package md.ceiti.internmanagerapp.front.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.formlayout.FormLayout;
import md.ceiti.internmanagerapp.back.util.ErrorUtils;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;

import java.util.List;

public class IMFormFactory {
    public static <T> DefaultCrudFormFactory<T> getDefaultFormFactory(Class<T> domainType) {
        DefaultCrudFormFactory<T> formFactory = new DefaultCrudFormFactory<>(domainType) {
            @Override
            protected void configureForm(FormLayout formLayout, List<HasValueAndElement> fields) {
                Component nameField = (Component) fields.get(0);
                formLayout.setColspan(nameField, 1);
            }
        };

        formFactory.setShowNotifications(true);
        formFactory.setErrorListener(e -> ErrorUtils.showError(e.getMessage()));
        formFactory.setUseBeanValidation(true);

        return formFactory;
    }
}
