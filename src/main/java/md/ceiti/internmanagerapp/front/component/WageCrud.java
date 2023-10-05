package md.ceiti.internmanagerapp.front.component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import md.ceiti.internmanagerapp.back.dto.AssignmentDto;
import md.ceiti.internmanagerapp.back.dto.JobDto;
import md.ceiti.internmanagerapp.back.dto.ProjectDto;
import md.ceiti.internmanagerapp.back.dto.WageDto;
import md.ceiti.internmanagerapp.back.dto.WorkRecordDto;
import md.ceiti.internmanagerapp.back.service.JobService;
import md.ceiti.internmanagerapp.back.service.ProjectService;
import md.ceiti.internmanagerapp.back.service.WageService;
import md.ceiti.internmanagerapp.back.util.ErrorUtils;
import md.ceiti.internmanagerapp.front.util.IMFormFactory;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.FindAllCrudOperationListener;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

public class WageCrud extends VerticalLayout {

    private final WageService wageService;
    private final ProjectService projectService;
    private final JobService jobService;

    public WageCrud() {
        this.wageService = new WageService(new RestTemplate());
        this.projectService = new ProjectService(new RestTemplate());
        this.jobService = new JobService(new RestTemplate());
        add(new H1("Wages"));
        add(getCrud());
    }

    private GridCrud<WageDto> getCrud() {
        GridCrud<WageDto> crud = getCrudWithGridCrudFactory();

        crud.getGrid().removeAllColumns();
        crud.getGrid().addColumn(wageDto -> wageDto.getProject().getName()).setHeader("Project").setSortable(true);
        crud.getGrid().addColumn(wageDto -> wageDto.getJob().getDisplayName()).setHeader("Job").setSortable(true);
        crud.getGrid().addColumn(WageDto::getPaymentPerHour).setHeader("Payment Per Hour").setSortable(true);
        crud.getGrid().setWidthFull();

        crud.setFindAllOperation(wageService::getAll);
        crud.setAddOperation(wageService::save);
        crud.setUpdateOperation(wageService::update);
        crud.setDeleteOperation(wageService::delete);

        return crud;
    }

    private GridCrud<WageDto> getCrudWithGridCrudFactory() {
        DefaultCrudFormFactory<WageDto> formFactory = IMFormFactory.getDefaultFormFactory(WageDto.class);
        formFactory.setVisibleProperties("project", "job", "paymentPerHour");

        formFactory.setFieldProvider("project",
                new ComboBoxProvider<>(projectService.getAll()));
        formFactory.setFieldProvider("project",
                new ComboBoxProvider<>("Project",
                        projectService.getAll(),
                        new TextRenderer<>(ProjectDto::getName),
                        ProjectDto::getName)
        );

        formFactory.setFieldProvider("job",
                new ComboBoxProvider<>(jobService.getAll()));
        formFactory.setFieldProvider("job",
                new ComboBoxProvider<>("Job",
                        jobService.getAll(),
                        new TextRenderer<>(JobDto::getDisplayName),
                        JobDto::getDisplayName)
        );

        return new GridCrud<>(WageDto.class, new HorizontalSplitCrudLayout(), formFactory);
    }
}
