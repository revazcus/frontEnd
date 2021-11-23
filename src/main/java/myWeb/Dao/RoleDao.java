package myWeb.Dao;

import myWeb.Model.Role;

import java.util.List;

public interface RoleDao {
    Role getAuthByName(String name);
    Role getRoleById(Long id);
    List<Role> getAllRoles();
}
