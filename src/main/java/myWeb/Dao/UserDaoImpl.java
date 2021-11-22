package myWeb.Dao;

import myWeb.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "user")
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select c from User c", User.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void saveUser(User user, String access) {
        User user1 = entityManager.merge(user);
        entityManager.persist(user1);
        entityManager.createNativeQuery("insert into usersandroles (user_id, role_id) values (?,?)")
                .setParameter(1, user1.getId())
                .setParameter(2, 1)
                .executeUpdate();
        if (access.equals("on")){
            entityManager.createNativeQuery("insert into usersandroles (user_id, role_id) values (?,?)")
                    .setParameter(1, user1.getId())
                    .setParameter(2, 2)
                    .executeUpdate();
        }
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        User user1 = entityManager.merge(user);
        entityManager.remove(user1);
    }



    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User loadUserByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM User u where u.username = :login", User.class)
                    .setParameter("login", username)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
