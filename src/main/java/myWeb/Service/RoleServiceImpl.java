package myWeb.Service;

import myWeb.Dao.RoleDao;
import myWeb.Dao.RoleDaoImpl;
import myWeb.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Role getAuthByName(String name) {
        return roleDao.getAuthByName(name);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
