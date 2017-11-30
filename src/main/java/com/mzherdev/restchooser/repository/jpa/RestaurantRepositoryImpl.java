package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.repository.RestaurantRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional(readOnly = true)
public class RestaurantRepositoryImpl implements RestaurantRepository {

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
    public Restaurant getBestOfTheDay(LocalDateTime day) {
        Query query = em.createNativeQuery("SELECT re.id FROM(SELECT r.id, r.name, r.description, count(v.restaurant_id) AS cnt FROM restaurants r LEFT JOIN votes v ON" +
                "                r.id = v.restaurant_id" +
                "      WHERE v.vote_time BETWEEN ?1 AND ?2" +
                "      GROUP BY r.id, r.name, r.description" +
                "      ORDER BY cnt DESC) re LIMIT 1");
         query.setParameter(1, day.withHour(0).withMinute(0).withSecond(0));
        query.setParameter(2, day.withHour(23).withMinute(59).withSecond(59));

        Object result = DataAccessUtils.singleResult(query.getResultList());
        return Objects.nonNull(result) ? get((Integer) result): null;
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.ALL, Restaurant.class).getResultList();
    }
}
