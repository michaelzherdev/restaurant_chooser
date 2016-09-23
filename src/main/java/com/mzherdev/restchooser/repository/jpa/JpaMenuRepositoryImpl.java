package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class JpaMenuRepositoryImpl implements MenuRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            log.info("save " + menu);
            em.persist(menu);
            log.info("save successful");
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Override
    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public Menu getForRestaurant(int id, int restaurantId) {
        List<Menu> menusForRestaurant = em.createNamedQuery(Menu.GET_ONE_FOR_RESTAURANT, Menu.class)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
        return DataAccessUtils.singleResult(menusForRestaurant);
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.ALL, Menu.class).getResultList();
    }
}
