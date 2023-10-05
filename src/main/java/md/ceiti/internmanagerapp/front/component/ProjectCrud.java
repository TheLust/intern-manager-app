package md.ceiti.internmanagerapp.front.component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import md.ceiti.internmanagerapp.back.dto.ProjectDto;
import md.ceiti.internmanagerapp.back.service.ProjectService;
import md.ceiti.internmanagerapp.front.util.IMFormFactory;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

public class ProjectCrud extends VerticalLayout {

    private final ProjectService projectService;

    public ProjectCrud() {
        this.projectService = new ProjectService(new RestTemplate());
        add(new H1("Projects"));
        add(getCrud());
    }

    private GridCrud<ProjectDto> getCrud() {
        GridCrud<ProjectDto> crud = getCrudWithGridCrudFactory();

        crud.getGrid().setColumns("id", "name");
        crud.getGrid().getColumnByKey("id").setVisible(false);


        crud.setFindAllOperation(projectService::getAll);
        crud.setAddOperation(projectService::save);
        crud.setUpdateOperation(projectService::update);
        crud.setDeleteOperation(projectService::delete);

        return crud;
    }

    private GridCrud<ProjectDto> getCrudWithGridCrudFactory() {
        DefaultCrudFormFactory<ProjectDto> formFactory = IMFormFactory.getDefaultFormFactory(ProjectDto.class);
        formFactory.setVisibleProperties("name");

        return new GridCrud<>(ProjectDto.class, new HorizontalSplitCrudLayout(), formFactory);
    }
}
