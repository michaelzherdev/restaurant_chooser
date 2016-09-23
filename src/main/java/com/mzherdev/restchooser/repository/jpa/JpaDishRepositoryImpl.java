package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.model.User;
import com.mzherdev.restchooser.repository.DishRepository;
import org.springframework.dao.support.DataAccessUtils;
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
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Dish get(int id) {
        return em.find(Dish.class, id);
    }

    @Override
    public Dish getForMenu(int id, int menuId) {
        List<Dish> dishesFromMenu = em.createNamedQuery(Dish.GET, Dish.class)
                .setParameter("id", id)
                .setParameter("menuId", menuId)
                .getResultList();
        return DataAccessUtils.singleResult(dishesFromMenu);
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.ALL, Dish.class).getResultList();
    }
}
