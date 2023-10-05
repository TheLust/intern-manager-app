package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.component.DepartmentCrud;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "/departments", layout = MainLayout.class)
public class DepartmentView extends VerticalLayout {

    public DepartmentView() {
        DepartmentCrud departmentCrud = new DepartmentCrud();
        add(departmentCrud);
    }
}
