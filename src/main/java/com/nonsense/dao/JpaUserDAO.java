package com.nonsense.dao;

import com.nonsense.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserDAO implements UserDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }

    @Override
    public User getOne(String email) {
        TypedQuery<User> q = entityManager.createQuery(
                "select u from User u where u.email = :email", User.class
        );
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }
}
