package myWeb.Controller;

import myWeb.Model.Role;
import myWeb.Model.User;
import myWeb.Service.RoleService;
import myWeb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping //Начальная страница админа
    public ModelAndView allUsers() {
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin");
        modelAndView.addObject("admin",admin);
        return modelAndView;
    }

    @GetMapping(value = "/api/users") //api отображения пользователей
    public ResponseEntity<List<User>> apiAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/api/users/{id}") //api отображения пользователей
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/api/users") //api создания нового пользователя
    public ResponseEntity<User> create(@RequestBody User user){
        userManipulation(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/api/users") //api редактирования
    public ResponseEntity<User> edit(@RequestBody User user){
        userManipulation(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private void userManipulation(User user) { //функция где должны вставляться роли пользователю и где сохраняется пользователь
        Set<Role> roleSet = new HashSet<>();
        if (user.getRoleSetTemp().length == 2){
            roleSet.add(roleService.getAuthByName("User"));
            roleSet.add(roleService.getAuthByName("Admin"));
        } else {
            if (user.getRoleSetTemp()[0].equals("Admin")) roleSet.add(roleService.getAuthByName("Admin"));
            else roleSet.add(roleService.getAuthByName("User"));
        }

        user.setRoleSet(roleSet);
        System.out.println(user);
        userService.saveUser(user);
    }

    @DeleteMapping(value = "/api/users/{id}") //api удаления
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(userService.getUserById(id));
        return new ResponseEntity(HttpStatus.OK);
    }

}
