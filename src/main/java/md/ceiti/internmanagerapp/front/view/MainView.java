package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {
    public MainView() {
        add(new H1("Hello, world"));
    }
}
