package myWeb.Config;

import myWeb.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Application.class);
    }
}



