package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.model.User;
import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        User userRef = em.getReference(User.class, userId);
        vote.setUser(userRef);
        Restaurant restaurantRef = em.getReference(Restaurant.class, restaurantId);
        vote.setRestaurant(restaurantRef);
        em.persist(vote);
        return vote;
    }

    @Override
    public Vote update(Vote vote, int restaurantId) {
        Restaurant restaurantRef = em.getReference(Restaurant.class, restaurantId);
        vote.setRestaurant(restaurantRef);
        vote = em.merge(vote);
        return vote;
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.ALL, Vote.class).getResultList();
    }

    @Override
    public List<Vote> getByUser(int userId) {
        return em.createNamedQuery(Vote.ALL_BY_USER, Vote.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Vote> getByRestaurant(int restaurantId) {
        return em.createNamedQuery(Vote.ALL_FOR_RESTAURANT, Vote.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }
}
