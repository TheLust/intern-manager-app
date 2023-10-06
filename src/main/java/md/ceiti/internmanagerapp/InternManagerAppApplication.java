package md.ceiti.internmanagerapp;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Theme("my-theme")
@SpringBootApplication
public class InternManagerAppApplication implements AppShellConfigurator {

    @Override
    public void configurePage(AppShellSettings settings) {
        settings.addLink("shortcut icon", "favicon.ico");
        settings.addFavIcon("icon", "favicon.ico", "48x48");
        AppShellConfigurator.super.configurePage(settings);
    }

    public static void main(String[] args) {
        SpringApplication.run(InternManagerAppApplication.class, args);
    }

}
