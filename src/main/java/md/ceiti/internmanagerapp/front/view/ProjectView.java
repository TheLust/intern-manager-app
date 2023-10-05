package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.component.ProjectCrud;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "/projects", layout = MainLayout.class)
public class ProjectView extends VerticalLayout {
    public ProjectView() {
        ProjectCrud projectCrud = new ProjectCrud();
        add(projectCrud);
    }
}
