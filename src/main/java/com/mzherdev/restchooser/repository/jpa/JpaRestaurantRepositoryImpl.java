package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.repository.RestaurantRepository;
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
public class JpaRestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
         if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public Menu getMenu(int id, int menuId) {
        List<Menu> menusForRestaurant = em.createNamedQuery(Menu.GET_ONE_FOR_RESTAURANT, Menu.class)
                .setParameter("id", menuId)
                .setParameter("restaurantId", id)
                .getResultList();
        return DataAccessUtils.singleResult(menusForRestaurant);
    }

    @Override
    public List<Menu> getMenus(int id) {
        return em.createNamedQuery(Menu.GET_ALL_FOR_RESTAURANT, Menu.class)
                .setParameter("restaurantId", id)
                .getResultList();
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class).getResultList();
    }
}
