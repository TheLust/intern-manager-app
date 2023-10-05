package md.ceiti.internmanagerapp.front.handler;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;
import md.ceiti.internmanagerapp.front.view.ErrorView;

public class CustomErrorHandler implements ErrorHandler {
    @Override
    public void error(ErrorEvent errorEvent) {
        UI.getCurrent().navigate(ErrorView.class);
    }
}
