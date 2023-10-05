package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.component.AssignmentCrud;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "/assignments", layout = MainLayout.class)
public class AssignmentView extends VerticalLayout {
    public AssignmentView() {
        AssignmentCrud assignmentCrud = new AssignmentCrud();
        add(assignmentCrud);
    }
}
