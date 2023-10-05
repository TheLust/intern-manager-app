package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.component.JobCrud;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "/jobs", layout = MainLayout.class)
public class JobView extends VerticalLayout {
    public JobView() {
        JobCrud jobCrud = new JobCrud();
        add(jobCrud);
    }
}
