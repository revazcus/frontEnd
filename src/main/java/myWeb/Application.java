package myWeb;

import myWeb.Dao.RoleDao;
import myWeb.Dao.UserDao;
import myWeb.Model.Role;
import myWeb.Model.User;
import myWeb.Service.RoleService;
import myWeb.Service.RoleServiceImpl;
import myWeb.Service.UserService;
import myWeb.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public Application(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    public static void main(String[] args) {



        SpringApplication.run(Application.class, args);


    }

    public void run(String... args) throws Exception {

        roleService.saveRole(new Role(1L,"Admin"));
        roleService.saveRole(new Role(2L,"User"));

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getAuthByName("Admin"));
        roleSet.add(roleService.getAuthByName("User"));

        Set<Role> roleSetUser = new HashSet<>();
        roleSetUser.add(roleService.getAuthByName("User"));

        User admin = new User("Admin","Man", (byte) 19,"Admin@mail","root",roleSet);

        User user = new User("User", "Guy", (byte) 17, "User@mail","user", roleSetUser);


        userService.saveUser(admin);
        userService.saveUser(user);
    }
}
