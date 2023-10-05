package md.ceiti.internmanagerapp.front.component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import md.ceiti.internmanagerapp.back.dto.AssignmentDto;
import md.ceiti.internmanagerapp.back.dto.EmployeeDto;
import md.ceiti.internmanagerapp.back.dto.ProjectDto;
import md.ceiti.internmanagerapp.back.service.AssignmentService;
import md.ceiti.internmanagerapp.back.service.EmployeeService;
import md.ceiti.internmanagerapp.back.service.ProjectService;
import md.ceiti.internmanagerapp.front.util.IMFormFactory;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

public class AssignmentCrud extends VerticalLayout {

    private final AssignmentService assignmentService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public AssignmentCrud() {
        this.assignmentService = new AssignmentService(new RestTemplate());
        this.projectService = new ProjectService(new RestTemplate());
        this.employeeService = new EmployeeService(new RestTemplate());
        add(new H1("Assignments"));
        add(getCrud());
    }

    private GridCrud<AssignmentDto> getCrud() {
        GridCrud<AssignmentDto> crud = getCrudWithGridCrudFactory();

        crud.getGrid().removeAllColumns();

        crud.getGrid().addColumn(assignmentDto -> assignmentDto.getProject().getName()).setHeader("Project").setSortable(true);
        crud.getGrid().addColumn(wageDto -> wageDto.getEmployee().getDisplayName()).setHeader("Employee").setSortable(true);

        crud.setFindAllOperation(assignmentService::getAll);
        crud.setAddOperation(assignmentService::save);
        crud.setUpdateOperation(assignmentService::update);
        crud.setDeleteOperation(assignmentService::delete);

        return crud;
    }

    private GridCrud<AssignmentDto> getCrudWithGridCrudFactory() {
        DefaultCrudFormFactory<AssignmentDto> formFactory = IMFormFactory.getDefaultFormFactory(AssignmentDto.class);
        formFactory.setVisibleProperties("project", "employee");

        formFactory.setFieldProvider("project",
                new ComboBoxProvider<>(projectService.getAll()));
        formFactory.setFieldProvider("project",
                new ComboBoxProvider<>("Project",
                        projectService.getAll(),
                        new TextRenderer<>(ProjectDto::getName),
                        ProjectDto::getName)
        );

        formFactory.setFieldProvider("employee",
                new ComboBoxProvider<>(employeeService.getAll()));
        formFactory.setFieldProvider("employee",
                new ComboBoxProvider<>("Employee",
                        employeeService.getAll(),
                        new TextRenderer<>(EmployeeDto::getDisplayName),
                        EmployeeDto::getDisplayName)
        );

        return new GridCrud<>(AssignmentDto.class, new HorizontalSplitCrudLayout(), formFactory);
    }
}
