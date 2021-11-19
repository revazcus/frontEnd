package myWeb.Dao;

import myWeb.Model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao{

    @PersistenceContext(unitName = "user")
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select c from User c", User.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        User user1 = entityManager.merge(user);
        entityManager.persist(user1);
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
}
