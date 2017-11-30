package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static com.mzherdev.restchooser.data.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void save() throws Exception {
        Restaurant restaurant = new Restaurant("NEW", "the best restaurant ever.");
        Restaurant created = service.save(restaurant);
        restaurant.setId(created.getId());
        assertMatch(service.getAll(), RESTAURANT_2, RESTAURANT_1, created, RESTAURANT_3);
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT_1_ID);
        assertMatch(Arrays.asList(RESTAURANT_2, RESTAURANT_3), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_1_ID);
        assertMatch(RESTAURANT_1, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_3);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(updated, service.get(RESTAURANT_3_ID));
    }

    @Test
    public void getAll() throws Exception {
        Collection<Restaurant> all = service.getAll();
        assertMatch(Arrays.asList(RESTAURANT_2, RESTAURANT_1, RESTAURANT_3), all);
    }

    @Test
    public void getBestOfTheDay() throws Exception {
        Restaurant best = service.getBestOfTheDay(LocalDateTime.of(2017, 12, 1, 0, 0));
        assertMatch(RESTAURANT_2, best);
    }

}