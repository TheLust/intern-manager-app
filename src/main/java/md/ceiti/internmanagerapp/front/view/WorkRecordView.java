package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.component.WorkRecordCrud;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "/time-sheets", layout = MainLayout.class)
public class WorkRecordView extends VerticalLayout {
    public WorkRecordView() {
        WorkRecordCrud workRecordCrud = new WorkRecordCrud();
        add(workRecordCrud);
    }
}
