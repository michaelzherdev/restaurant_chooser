package com.mzherdev.restchooser.repository.jpa;

import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.repository.VoteRepository;
import com.mzherdev.restchooser.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class JpaVoteRepositoryImpl implements VoteRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.getId() == null) {
            em.persist(vote);
            return vote;
        } else {
            if (DateTimeUtil.canChangeVote(vote.getVoteTime())) {
                log.info("update successful");
                return em.merge(vote);
            } else {
                log.info("update failed");
//                throw new VoteUpdateImpossibleException("Vote Update Impossible after 11 a.m.");
                return null;
            }
        }
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.ALL, Vote.class).getResultList();
    }
}
