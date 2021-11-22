package myWeb.Service;


import myWeb.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user, String access);
    void deleteUser(User user);
    User getUserById(Long id);
}
