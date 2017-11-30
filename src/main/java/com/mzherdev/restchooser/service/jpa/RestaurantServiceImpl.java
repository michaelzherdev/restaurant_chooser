package com.mzherdev.restchooser.service.jpa;

import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.repository.RestaurantRepository;
import com.mzherdev.restchooser.service.RestaurantService;
import com.mzherdev.restchooser.util.JpaUtil;
import com.mzherdev.restchooser.util.ValidationUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ValidationUtil.check(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return ValidationUtil.check(repository.get(id), id);
    }

    @Override
    public void update(Restaurant restaurant) throws ReflectiveOperationException {
        Restaurant updatable  = get(restaurant.getId());
        restaurant = JpaUtil.updateEntity(updatable, restaurant);
        repository.save(restaurant); }

    @Override
    public List<Restaurant> getAll() { return  repository.getAll(); }

    @Override
    public Restaurant getBestOfTheDay(LocalDateTime day) {
        return repository.getBestOfTheDay(day);
    }
}
