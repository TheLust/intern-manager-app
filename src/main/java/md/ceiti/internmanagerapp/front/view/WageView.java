package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.component.WageCrud;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "/wages", layout = MainLayout.class)
public class WageView extends VerticalLayout {
    public WageView() {
        WageCrud wageCrud = new WageCrud();
        add(wageCrud);
    }
}
