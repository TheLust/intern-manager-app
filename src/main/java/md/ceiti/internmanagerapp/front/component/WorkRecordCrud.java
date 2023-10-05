package md.ceiti.internmanagerapp.front.component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TextRenderer;
import md.ceiti.internmanagerapp.back.dto.AssignmentDto;
import md.ceiti.internmanagerapp.back.dto.WorkRecordDto;
import md.ceiti.internmanagerapp.back.service.AssignmentService;
import md.ceiti.internmanagerapp.back.service.WorkRecordService;
import md.ceiti.internmanagerapp.front.util.IMFormFactory;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.FindAllCrudOperationListener;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

public class WorkRecordCrud extends VerticalLayout {

    private final WorkRecordService workRecordService;
    private final AssignmentService assignmentService;

    TextField employeeFilter;
    TextField dateFilter;

    public WorkRecordCrud() {
        this.workRecordService = new WorkRecordService(new RestTemplate());
        this.assignmentService = new AssignmentService(new RestTemplate());
        employeeFilter = new TextField();
        dateFilter = new TextField();
        add(new H1("Records"));
        add(getCrud());
    }

    private GridCrud<WorkRecordDto> getCrud() {
        GridCrud<WorkRecordDto> crud = getCrudWithGridCrudFactory();

        crud.getGrid().removeAllColumns();
        crud.getGrid().addColumn(WorkRecordDto::getDate)
                .setHeader("Date")
                .setSortable(true);
        crud.getGrid().addColumn(workRecordDto -> workRecordDto.getAssignment().getProject().getName())
                .setHeader("Project")
                .setSortable(true);
        crud.getGrid().addColumn(workRecordDto -> workRecordDto.getAssignment().getEmployee().getDisplayName())
                .setHeader("Employee")
                .setSortable(true);
        crud.getGrid().addColumn(WorkRecordDto::getWorkedHours)
                .setHeader("Worked Hours")
                .setSortable(true);
        crud.getGrid().addColumn(WorkRecordDto::getAmount)
                .setHeader("Income")
                .setSortable(true);
        crud.getGrid().setWidthFull();

        crud.setFindAllOperation((FindAllCrudOperationListener<WorkRecordDto>)
                () -> workRecordService.findByEmployeeAndDate(employeeFilter.getValue(), dateFilter.getValue()));
        crud.setAddOperation(workRecordService::save);
        crud.setUpdateOperation(workRecordService::update);
        crud.setDeleteOperation(workRecordService::delete);

        setFilters(crud);

        return crud;
    }

    private GridCrud<WorkRecordDto> getCrudWithGridCrudFactory() {
        DefaultCrudFormFactory<WorkRecordDto> formFactory = IMFormFactory.getDefaultFormFactory(WorkRecordDto.class);
        formFactory.setVisibleProperties("assignment", "date", "workedHours");

        formFactory.setFieldProvider(
                "assignment",
                new ComboBoxProvider<>(
                        assignmentService.getAll()
                )
        );
        formFactory.setFieldProvider(
                "assignment",
                new ComboBoxProvider<>(
                        "Assignment",
                        assignmentService.getAll(),
                        new TextRenderer<>(AssignmentDto::getDisplayName),
                        AssignmentDto::getDisplayName
                )
        );

        return new GridCrud<>(WorkRecordDto.class, new HorizontalSplitCrudLayout(), formFactory);
    }

    private void setFilters(GridCrud<WorkRecordDto> crud) {
        employeeFilter.setPlaceholder("Filter by employee");
        employeeFilter.setClearButtonVisible(true);
        employeeFilter.addValueChangeListener(e -> crud.refreshGrid());

        dateFilter.setPlaceholder("Filter by date");
        dateFilter.setClearButtonVisible(true);
        dateFilter.addValueChangeListener(e -> crud.refreshGrid());

        crud.getCrudLayout().addFilterComponents(employeeFilter, dateFilter);
    }
}
