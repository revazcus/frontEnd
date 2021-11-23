package myWeb.Dao;

import myWeb.Model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext(unitName = "user")
    private EntityManager entityManager;

    @Override
    public Role getAuthByName(String role) {
        try {
            return (Role) entityManager.createQuery("select r from Role r where r.role = :role ")
                    .setParameter("role", role)
                    .getSingleResult();
        } catch (Exception e){
            System.out.println("Role is non-existent");
        }
        return null;
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class)
                .getResultList();
    }

}
