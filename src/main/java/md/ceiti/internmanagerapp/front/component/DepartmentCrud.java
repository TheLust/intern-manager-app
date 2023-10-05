package md.ceiti.internmanagerapp.front.component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.SneakyThrows;
import md.ceiti.internmanagerapp.back.dto.DepartmentDto;
import md.ceiti.internmanagerapp.back.service.DepartmentService;
import md.ceiti.internmanagerapp.front.util.IMFormFactory;
import org.springframework.web.client.RestTemplate;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;


public class DepartmentCrud extends VerticalLayout {

    private final DepartmentService departmentService;

    @SneakyThrows
    public DepartmentCrud() {
        this.departmentService = new DepartmentService(new RestTemplate());
        add(new H1("Departments"));
        setSizeFull();
        add(getCrud());
    }

    private GridCrud<DepartmentDto> getCrud() {
        GridCrud<DepartmentDto> crud = getCrudWithGridCrudFactory();

        crud.getGrid().setColumns("id", "name");
        crud.getGrid().getColumnByKey("id").setVisible(false);
        crud.getGrid().setColumnReorderingAllowed(true);

        crud.setFindAllOperation(departmentService::getAll);
        crud.setAddOperation(departmentService::save);
        crud.setUpdateOperation(departmentService::update);
        crud.setDeleteOperation(departmentService::delete);

        return crud;
    }

    private GridCrud<DepartmentDto> getCrudWithGridCrudFactory() {
        DefaultCrudFormFactory<DepartmentDto> formFactory = IMFormFactory.getDefaultFormFactory(DepartmentDto.class);
        formFactory.setVisibleProperties("name");

        return new GridCrud<>(DepartmentDto.class, new HorizontalSplitCrudLayout(), formFactory);
    }
}
