package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "/server-error", layout = MainLayout.class)
public class ErrorView extends VerticalLayout {
    public ErrorView() {
        setClassName("error-div");

        IFrame iFrame = new IFrame("error.html");
        iFrame.setClassName("error-frame");
        add(iFrame);
    }
}
