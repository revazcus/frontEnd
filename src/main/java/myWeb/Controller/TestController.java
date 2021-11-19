package myWeb.Controller;

import myWeb.Model.User;
import myWeb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

//Ignore
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public ModelAndView allUsers() {
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userList");
        modelAndView.addObject("userlst", users);
        return modelAndView;
    }

    @GetMapping(value = "/newUser")
    public String newUser(Model model){
        model.addAttribute("user", new User());

        return "/newUser";
    }

    @GetMapping(value = "/editUser/{id}")
    public ModelAndView editUser(@PathVariable("id") long id){
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/newUser")
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/editUser")
    public String edit(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/";
    }

}
