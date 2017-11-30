package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class MenuRepositoryImpl implements MenuRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        Restaurant ref = em.getReference(Restaurant.class, restaurantId);
        menu.setRestaurant(ref);
        em.persist(menu);
        return menu;
    }

    @Override
    @Transactional
    public Menu update(Menu menu) {
        return em.merge(menu);
    }

    @Override
    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public Menu getOneForRestaurant(int id, int restaurantId) {
        List<Menu> menusForRestaurant = em.createNamedQuery(Menu.GET_ONE_FOR_RESTAURANT, Menu.class)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
        return DataAccessUtils.singleResult(menusForRestaurant);
    }

    @Override
    public List<Menu> getAllForRestaurant(int restaurantId) {
        return em.createNamedQuery(Menu.GET_ALL_FOR_RESTAURANT, Menu.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }

    @Override
    public List<Menu> getActuals() {
        return em.createNativeQuery("SELECT m1.* FROM menus AS m1\n" +
                "  INNER JOIN\n" +
                "  (SELECT restaurant_id, max(day) AS day FROM menus\n" +
                "  GROUP BY restaurant_id) AS m2\n" +
                "    ON m1.restaurant_id = m2.restaurant_id\n" +
                "       AND m1.day = m2.day", Menu.class).getResultList();
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.ALL, Menu.class).getResultList();
    }
}
