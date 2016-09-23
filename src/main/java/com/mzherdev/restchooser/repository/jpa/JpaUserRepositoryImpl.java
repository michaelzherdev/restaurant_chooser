package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.User;
import com.mzherdev.restchooser.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        return em.createNamedQuery(User.BY_EMAIL, User.class).setParameter(1, email).getSingleResult();
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
