package myWeb.Controller;

import myWeb.Model.Role;
import myWeb.Model.User;
import myWeb.Service.RoleService;
import myWeb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Ignore
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/admin/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Welcome to my web page.");
        messages.add("Below are your available actions:");
        model.addAttribute("messages", messages);
        return "/admin/hello";
    }

    @GetMapping(value = "/admin/userList")
    public ModelAndView allUsers() {
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/userList");
        modelAndView.addObject("userlst", users);
        return modelAndView;
    }

    @GetMapping(value = "/admin/newUser")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "admin/newUser";
    }

    @PostMapping("/admin/newUser")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(required = false) String adminAuth){
        return userManipulation(user, adminAuth);
    }

    @GetMapping(value = "/admin/editUser/{id}")
    public ModelAndView editUser(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserById(id);
        Set<Role> roleSet = user.getRoleSet();
        if (roleSet.size() == 2) modelAndView.addObject("authAdmin",true);
        modelAndView.setViewName("admin/editUser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/admin/editUser")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam(required = false) String adminAuth){
        return userManipulation(user, adminAuth);
    }

    private String userManipulation(User user, String adminAuth) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getAuthByName("User"));
        if (adminAuth != null && adminAuth.equals("Admin"))
            roleSet.add(roleService.getAuthByName("Admin"));

        user.setRoleSet(roleSet);
        userService.saveUser(user);

        return "redirect:/admin/userList";
    }

    @GetMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/admin/userList";
    }

}
