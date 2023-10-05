package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.component.EmployeeCrud;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "/employees", layout = MainLayout.class)
public class EmployeeView extends VerticalLayout {
    public EmployeeView() {
        EmployeeCrud employeeCrud = new EmployeeCrud();
        add(employeeCrud);
    }
}
