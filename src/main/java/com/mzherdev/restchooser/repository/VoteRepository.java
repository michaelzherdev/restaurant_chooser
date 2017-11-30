package com.mzherdev.restchooser.repository;

import com.mzherdev.restchooser.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    Vote update(Vote vote, int restaurantId);

    Vote get(int id);

    List<Vote> getAll();

    List<Vote> getByUser(int userId);

    List<Vote> getByRestaurant(int restaurantId);
}
