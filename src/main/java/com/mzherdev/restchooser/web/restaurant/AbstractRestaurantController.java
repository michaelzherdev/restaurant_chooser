package com.mzherdev.restchooser.web.restaurant;

import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.service.RestaurantService;
import com.mzherdev.restchooser.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Restaurant get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public Restaurant getBestOfTheDay(LocalDateTime day) {
        log.info("getBestOfTheDay " + day);
        return service.getBestOfTheDay(day);
    }

    public Restaurant create(Restaurant restaurant) {
        restaurant.setId(null);
        log.info("create " + restaurant);
        return service.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(Restaurant restaurant, int id) throws ReflectiveOperationException {
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update " + restaurant);
        service.update(restaurant);
    }
}
