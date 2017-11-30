package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.util.List;

public interface VoteService {

    Vote save(Vote vote, int userId, int restaurantId);

    void update(Vote vote, int userId, int restaurantId) throws ReflectiveOperationException;

    Vote get(int id) throws NotFoundException;

    List<Vote> getAll();

    List<Vote> getByUser(int userId);

    List<Vote> getByRestaurant(int restaurantId);
}
