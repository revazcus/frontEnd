package myWeb.Controller;

import myWeb.Config.ORM_Config;
import myWeb.Model.User;
import myWeb.Service.UserService;
import myWeb.Service.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

//Ignore
@Controller
public class TestController {

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ORM_Config.class);
        UserService userService = context.getBean(UserService.class);
        List <User> userList = userService.getAllUsers();
        model.addAttribute("userlst", userList);
        return "index";

    }

}
