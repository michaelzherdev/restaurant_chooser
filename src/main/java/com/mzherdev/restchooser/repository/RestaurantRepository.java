package com.mzherdev.restchooser.repository;

import com.mzherdev.restchooser.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    // can be more than 1 if vote count is similar
    Restaurant getBestOfTheDay(LocalDateTime day);

    List<Restaurant> getAll();
}
