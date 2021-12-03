package myWeb.Controller;

import myWeb.Model.Role;
import myWeb.Model.User;
import myWeb.Service.RoleService;
import myWeb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping(value = "/admin")
    public ModelAndView allUsers() {
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> users = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin");
        modelAndView.addObject("userlst", users);
        modelAndView.addObject("admin",admin);
        return modelAndView;
    }

    @GetMapping(value = "/admin/newUser")
    public String newUser(Model model){
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admin", admin);
        model.addAttribute("user", new User());
        return "admin/newUser";
    }

    @PostMapping("/admin/newUser")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(required = false) String[] auth){
        return userManipulation(user, auth);
    }

    @GetMapping(value = "/admin/findUser")
    @ResponseBody
    public User editUser(@RequestParam("id") long id){
        return userService.getUserById(id);
    }

    @PostMapping("/admin/editUser")
    public String edit(@ModelAttribute("user") User user,
                       @RequestParam(required = false) String[] auth){
        return userManipulation(user, auth);
    }

    private String userManipulation(User user, String[] auth) {
        Set<Role> roleSet = new HashSet<>();
        if (auth.length == 2){
            roleSet.add(roleService.getAuthByName("User"));
            roleSet.add(roleService.getAuthByName("Admin"));
        } else {
            if (auth[0].equals("Admin")) roleSet.add(roleService.getAuthByName("Admin"));
            else roleSet.add(roleService.getAuthByName("User"));
        }

        user.setRoleSet(roleSet);
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @PostMapping(value = "admin/deleteUser")
    public String deleteUser(@RequestParam("id") Long id){
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/admin";
    }

}
