package com.mzherdev.restchooser.web.vote;

import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mzherdev on 07.06.2016.
 */
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

    public Vote create(Vote vote) {
        vote.setId(null);
        log.info("create " + vote);
        return service.save(vote);
    }

    public void update(Vote vote, int id) {
        vote.setId(id);
        log.info("update " + vote);
        service.update(vote);
    }

    public void update(Vote vote) {
        log.info("update " + vote);
        service.update(vote);
    }
}
