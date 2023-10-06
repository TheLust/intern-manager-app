package md.ceiti.internmanagerapp.front.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import md.ceiti.internmanagerapp.front.layout.MainLayout;

@Route(value = "", layout = MainLayout.class)
@CssImport("./themes/my-theme/component-styles/bootstrap.css")
@CssImport("./themes/my-theme/component-styles/responsive.css")
@CssImport("./themes/my-theme/component-styles/style.css")
@PageTitle("IM")
public class MainView extends VerticalLayout {
    public MainView() {

        // Wrapper
        Div heroArea = new Div();
        heroArea.addClassName("hero_area");

        // Header Section
        Header header = new Header();
        header.addClassName("header_section");
        heroArea.add(header);

        Div containerFluid = new Div();
        containerFluid.addClassName("container-fluid");
        header.add(containerFluid);

        Nav nav = new Nav();
        nav.addClassNames("navbar", "navbar-expand-lg", "custom_nav-container");
        containerFluid.add(nav);

        Anchor returnToHomePage = new Anchor();
        returnToHomePage.addClassName("navbar-brand");
        nav.add(returnToHomePage);

        Image img = new Image();
        img.setSrc("./favicon.ico");

        Span span = new Span();
        span.setText("Intern Manager");

        returnToHomePage.add(img);
        returnToHomePage.add(span);

        Button button = new Button();
        button.addClassName("navbar-toggler");

        Span btnSpan = new Span();
        span.addClassName("navbar-toggles-icon");

        add(heroArea);
    }
}
