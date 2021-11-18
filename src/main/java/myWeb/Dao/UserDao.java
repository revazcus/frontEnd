package myWeb.Dao;

import myWeb.Model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUser(User user);
    User getUserById(Long id);
}
