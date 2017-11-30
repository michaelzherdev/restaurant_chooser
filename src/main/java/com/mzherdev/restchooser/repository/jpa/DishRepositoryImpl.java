package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.repository.DishRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class DishRepositoryImpl implements DishRepository {

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
        return em.createNamedQuery(Dish.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Dish get(int id) {
        return em.find(Dish.class, id);
    }

    @Override
    public Dish getForMenu(int id, int menuId) {
        Query query = em.createNativeQuery("SELECT d.id, d.name, d.price FROM dishes d " +
                "LEFT JOIN menu_dish_link dl ON d.id = dl.dish_id " +
                "WHERE d.id = ?1 AND dl.menu_id = ?2", Dish.class);
        query.setParameter(1, id);
        query.setParameter(2, menuId);
        Object result = DataAccessUtils.singleResult(query.getResultList());
        return Objects.nonNull(result) ? (Dish) result : null;
    }

    @Override
    public List<Dish> getAllForMenu(int menuId) {
        Query query = em.createNativeQuery("SELECT d.id, d.name, d.price FROM dishes d " +
                "LEFT JOIN menu_dish_link dl ON d.id = dl.dish_id " +
                "WHERE dl.menu_id = ?1", Dish.class);
        query.setParameter(1, menuId);
        return query.getResultList();
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.ALL, Dish.class).getResultList();
    }
}
