package myWeb.Service;

import myWeb.Dao.RoleDao;
import myWeb.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getAuthByName(String name) {
        return roleDao.getAuthByName(name);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.findById(id)
                .orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role){
        roleDao.save(role);
    }
}
