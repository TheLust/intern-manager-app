package md.ceiti.internmanagerapp.front.component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import md.ceiti.internmanagerapp.back.dto.JobDto;
import md.ceiti.internmanagerapp.back.enums.Stage;
import md.ceiti.internmanagerapp.back.service.JobService;
import md.ceiti.internmanagerapp.front.util.IMFormFactory;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.List;

public class JobCrud extends VerticalLayout {

    private final JobService jobService;

    public JobCrud() {
        this.jobService = new JobService(new RestTemplate());
        add(new H1("Jobs"));
        add(getCrud());
    }

    private GridCrud<JobDto> getCrud() {
        GridCrud<JobDto> crud = getCrudWithGridCrudFactory();

        crud.getGrid().setColumns("id", "name", "stage", "baseSalary");
        crud.getGrid().getColumnByKey("stage").setRenderer(new TextRenderer<>(jobDto -> jobDto.getStage().getDisplayName()));
        crud.getGrid().getColumnByKey("id").setVisible(false);
        crud.getGrid().setPageSize(15);

        crud.setFindAllOperation(jobService::getAll);
        crud.setAddOperation(jobService::save);
        crud.setUpdateOperation(jobService::update);
        crud.setDeleteOperation(jobService::delete);

        return crud;
    }

    private GridCrud<JobDto> getCrudWithGridCrudFactory() {
        DefaultCrudFormFactory<JobDto> formFactory = IMFormFactory.getDefaultFormFactory(JobDto.class);
        formFactory.setVisibleProperties("name", "stage", "baseSalary");

        formFactory.setFieldProvider("stage",
                new ComboBoxProvider<>(jobService.getAll()));
        formFactory.setFieldProvider("stage",
                new ComboBoxProvider<>("Stage",
                        List.of(Stage.values()),
                        new TextRenderer<>(Stage::getDisplayName),
                        Stage::getDisplayName)
        );

        return new GridCrud<>(JobDto.class, new HorizontalSplitCrudLayout(), formFactory);
    }
}
