package md.ceiti.internmanagerapp.front.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import md.ceiti.internmanagerapp.back.dto.DepartmentDto;
import md.ceiti.internmanagerapp.back.dto.EmployeeDto;
import md.ceiti.internmanagerapp.back.dto.EmployeeIncome;
import md.ceiti.internmanagerapp.back.dto.JobDto;
import md.ceiti.internmanagerapp.back.dto.ProjectDto;
import md.ceiti.internmanagerapp.back.dto.WageDto;
import md.ceiti.internmanagerapp.back.service.DepartmentService;
import md.ceiti.internmanagerapp.back.service.EmployeeService;
import md.ceiti.internmanagerapp.back.service.JobService;
import md.ceiti.internmanagerapp.back.util.ErrorUtils;
import md.ceiti.internmanagerapp.front.util.IMFormFactory;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class EmployeeCrud extends VerticalLayout {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final JobService jobService;

    public EmployeeCrud() {
        this.employeeService = new EmployeeService(new RestTemplate());
        this.departmentService = new DepartmentService(new RestTemplate());
        this.jobService = new JobService(new RestTemplate());
        add(new H1("Employees"));
        add(getCrud());
    }

    private GridCrud<EmployeeDto> getCrud() {
        GridCrud<EmployeeDto> crud = getCrudWithGridCrudFactory();

        crud.getGrid().setColumns("id", "firstName", "lastName", "gender",
                "dateOfBirth", "email", "phoneNumber", "dateOfHire",
                "bonusSalary");
        crud.getGrid().addColumn(employeeDto -> employeeDto.getDepartment().getName()).setHeader("Department").setSortable(true);
        crud.getGrid().addColumn(employeeDto -> employeeDto.getJob().getDisplayName()).setHeader("Job").setSortable(true);
        crud.getGrid().getColumnByKey("id").setVisible(false);
        crud.getGrid().setDetailsVisibleOnClick(true);


        crud.setFindAllOperation(employeeService::getAll);
        crud.setAddOperation(employeeService::save);
        crud.setUpdateOperation(employeeService::update);
        crud.setDeleteOperation(employeeService::delete);

        addContextMenu(crud);

        return crud;
    }

    private GridCrud<EmployeeDto> getCrudWithGridCrudFactory() {
        DefaultCrudFormFactory<EmployeeDto> formFactory = IMFormFactory.getDefaultFormFactory(EmployeeDto.class);
        formFactory.setVisibleProperties("firstName", "lastName", "gender",
                "dateOfBirth", "email", "phoneNumber", "dateOfHire",
                "bonusSalary", "department", "job");

        formFactory.setFieldProvider("department",
                new ComboBoxProvider<>(departmentService.getAll()));
        formFactory.setFieldProvider("department",
                new ComboBoxProvider<>("Department",
                        departmentService.getAll(),
                        new TextRenderer<>(DepartmentDto::getName),
                        DepartmentDto::getName)
        );

        formFactory.setFieldProvider("job",
                new ComboBoxProvider<>(jobService.getAll()));
        formFactory.setFieldProvider("job",
                new ComboBoxProvider<>("Job",
                        jobService.getAll(),
                        new TextRenderer<>(JobDto::getDisplayName),
                        JobDto::getDisplayName)
        );

        return new GridCrud<>(EmployeeDto.class, new HorizontalSplitCrudLayout(), formFactory);
    }

    private void addContextMenu(GridCrud<EmployeeDto> crud) {
        GridContextMenu<EmployeeDto> contextMenu = crud.getGrid().addContextMenu();
        contextMenu.addItem("Calculate salary",
                employeeDtoGridContextMenuItemClickEvent -> {
                    if (employeeDtoGridContextMenuItemClickEvent.getItem().isEmpty()) {
                        ErrorUtils.showError("Invalid target");
                    } else {
                        openDialog(employeeDtoGridContextMenuItemClickEvent.getItem().get());
                    }
                });
    }

    private void openDialog(EmployeeDto employeeDto) {
        Dialog dialog = new Dialog();

        dialog.setClassName("salary-dialog");
        dialog.setCloseOnOutsideClick(true);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(Alignment.CENTER);

        Span monthSpan = new Span("Month");
        horizontalLayout.add(monthSpan);

        Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        ComboBox<Integer> monthComboBox = new ComboBox<>("", Arrays.stream(months).toList());
        horizontalLayout.add(monthComboBox);

        Button calculateButton = new Button("Calculate");
        calculateButton.addClickListener(buttonClickEvent -> openGrid(employeeDto, monthComboBox.getValue()));
        horizontalLayout.add(calculateButton);

        dialog.add(horizontalLayout);
        dialog.open();
    }

    private void openGrid(EmployeeDto employee, Integer month) {
        Dialog dialog = new Dialog();
        dialog.setWidthFull();

        VerticalLayout verticalLayout = new VerticalLayout();

        Grid<EmployeeIncome> grid = new Grid<>(EmployeeIncome.class, false);
        grid.addColumn(employeeIncome -> employeeIncome.getProject().getName()).setHeader("Project");
        grid.addColumn(EmployeeIncome::getPaymentPerHour).setHeader("Payment Per Hour");
        grid.addColumn(EmployeeIncome::getWorkedHours).setHeader("Worked Hours");
        grid.addColumn(EmployeeIncome::getIncome).setHeader("Income");

        List<EmployeeIncome> incomes = employeeService.getAllEmployeeIncomes(employee.getId(), month);
        grid.setItems(incomes);

        verticalLayout.add(grid);

        HorizontalLayout total = new HorizontalLayout();
        total.setAlignItems(Alignment.END);
        Span totalSpan = new Span("Total: " +
                incomes.stream()
                        .map(EmployeeIncome::getIncome)
                        .reduce(0D, Double::sum)
        );
        totalSpan.getStyle().set("margin-left", "auto")
                .set("margin-right", "16px");
        total.add(totalSpan);

        dialog.add(verticalLayout);
        dialog.add(total);
        dialog.open();
    }
}
