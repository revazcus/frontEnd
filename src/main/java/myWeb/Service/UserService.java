package myWeb.Service;


import myWeb.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUser(User user);
    User getUserById(Long id);
}
