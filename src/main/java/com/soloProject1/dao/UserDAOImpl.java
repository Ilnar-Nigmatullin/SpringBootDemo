package com.soloProject1.dao;

import com.soloProject1.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

//    @PersistenceContext
//    public UserDAOImpl (EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @SuppressWarnings("unchecked")
    public List<User> readUsers() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public void editUser(User user) {
        entityManager.merge(user);
    }

    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    public User findUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findByUserName(String userName) {
        Query query = entityManager.createQuery("from User where userName= :userName");
        query.setParameter("userName", userName).getSingleResult();

        return (User) query;
    }
}
