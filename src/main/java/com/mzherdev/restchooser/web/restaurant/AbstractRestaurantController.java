package com.mzherdev.restchooser.web.restaurant;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mzherdev on 07.06.2016.
 */
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

    public Restaurant create(Restaurant restaurant) {
        restaurant.setId(null);
        log.info("create " + restaurant);
        return service.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        restaurant.setId(id);
        log.info("update " + restaurant);
        service.update(restaurant);
    }

    public void update(Restaurant restaurant) {
        log.info("update " + restaurant);
        service.update(restaurant);
    }
    public Menu getMenu(int id, int menuId) {
        log.info("getMenu " + menuId);
        return service.getMenu(id, menuId);
    }

    public List<Menu> getMenus(int id) {
        log.info("getMenus " + id);
        return service.getMenus(id);
    }
}
