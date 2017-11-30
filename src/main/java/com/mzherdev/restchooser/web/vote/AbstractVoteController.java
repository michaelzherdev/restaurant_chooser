package com.mzherdev.restchooser.web.vote;

import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.service.VoteService;
import com.mzherdev.restchooser.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public List<Vote> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Vote get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public List<Vote> getAllForRestaurant(int id) {
        log.info("get " + id);
        return service.getByRestaurant(id);
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        log.info("create " + vote);
        return service.save(vote, userId, restaurantId);
    }

    public void update(Vote vote, int id, Integer userId, int restaurantId) throws ReflectiveOperationException{
        ValidationUtil.assureIdConsistent(vote, id);
        log.info("update " + vote);
        service.update(vote, userId, restaurantId);
    }
}
