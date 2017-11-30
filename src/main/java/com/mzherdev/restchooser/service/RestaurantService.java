package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    void update(Restaurant restaurant) throws ReflectiveOperationException;

    List<Restaurant> getAll();

    Restaurant getBestOfTheDay(LocalDateTime day);
}
