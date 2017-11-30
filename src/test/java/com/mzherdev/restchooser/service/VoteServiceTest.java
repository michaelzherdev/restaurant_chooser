package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import com.mzherdev.restchooser.util.exception.VoteUpdateImpossibleException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.mzherdev.restchooser.data.RestaurantTestData.RESTAURANT_1_ID;
import static com.mzherdev.restchooser.data.RestaurantTestData.RESTAURANT_2_ID;
import static com.mzherdev.restchooser.data.UserTestData.ADMIN_ID;
import static com.mzherdev.restchooser.data.UserTestData.USER_ID;
import static com.mzherdev.restchooser.data.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService service;

    @Test
    public void save() throws Exception {
        Vote vote = new Vote();
        Vote created = service.save(vote, USER_ID, RESTAURANT_1_ID);
        assertMatch(service.getAll(), VOTE,  created);
    }

    @Test
    public void updateBefore11AM() throws Exception {
        Vote updated = new Vote(VOTE);
        updated.setVoteTime(LocalDateTime.of(2017, 11, 1, 10, 59));
        service.update(updated, USER_ID, RESTAURANT_2_ID);
        assertMatch(updated, service.get(VOTE_ID));
    }

    @Test(expected = VoteUpdateImpossibleException.class)
    public void updateAfter11AM() throws Exception {
        Vote updated = new Vote(VOTE);
        updated.setVoteTime(LocalDateTime.of(2017, 11, 1, 11, 1));
        service.update(updated, USER_ID, RESTAURANT_2_ID);
        assertMatch(updated, service.get(VOTE_ID));
    }

    @Test
    public void get() throws Exception {
        Vote vote = service.get(VOTE_ID);
        assertMatch(vote, VOTE);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(), VOTE);
    }

    @Test
    public void getByRestaurant() {
        assertMatch(service.getByRestaurant(RESTAURANT_2_ID), VOTE);
    }

    @Test
    public void getByRestaurantNotFound() {
        assertMatch(service.getByRestaurant(RESTAURANT_1_ID), Collections.emptyList());
    }

    @Test
    public void getByUser() {
        assertMatch(service.getByUser(USER_ID), VOTE);
    }

    @Test
    public void getByUserNotFound() {
        assertMatch(service.getByUser(ADMIN_ID), Collections.emptyList());
    }

}