package md.ceiti.internmanagerapp.front.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import md.ceiti.internmanagerapp.front.handler.CustomErrorHandler;
import md.ceiti.internmanagerapp.front.util.Constants;
import md.ceiti.internmanagerapp.front.view.AssignmentView;
import md.ceiti.internmanagerapp.front.view.DepartmentView;
import md.ceiti.internmanagerapp.front.view.EmployeeView;
import md.ceiti.internmanagerapp.front.view.JobView;
import md.ceiti.internmanagerapp.front.view.MainView;
import md.ceiti.internmanagerapp.front.view.ProjectView;
import md.ceiti.internmanagerapp.front.view.WageView;
import md.ceiti.internmanagerapp.front.view.WorkRecordView;

public class MainLayout extends AppLayout {

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1(Constants.APP_NAME);
        title.addClickListener(e -> UI.getCurrent().navigate(""));
        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(toggle, title);

        VaadinSession.getCurrent().setErrorHandler(new CustomErrorHandler());
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.HOME, Constants.HOME, MainView.class),
                createTab(VaadinIcon.WORKPLACE, Constants.DEPARTMENTS, DepartmentView.class),
                createTab(VaadinIcon.SUITCASE, Constants.JOBS, JobView.class),
                createTab(VaadinIcon.FOLDER, Constants.PROJECTS, ProjectView.class),
                createTab(VaadinIcon.USERS, Constants.EMPLOYEES, EmployeeView.class),
                createTab(VaadinIcon.GRID_BIG, Constants.ASSIGNMENTS, AssignmentView.class),
                createTab(VaadinIcon.DIAMOND, Constants.WAGES, WageView.class),
                createTab(VaadinIcon.CLOCK, Constants.TIME_SHEETS, WorkRecordView.class));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> clazz) {
        Icon icon = viewIcon.create();

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(clazz);
        // Demo has no routes
        // link.setRoute(viewClass.java);
        link.setTabIndex(-1);

        return new Tab(link);
    }

}
