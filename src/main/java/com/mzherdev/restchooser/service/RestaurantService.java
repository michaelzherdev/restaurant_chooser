package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    Menu getMenu(int id, int menuId) throws NotFoundException;

    List<Menu> getMenus(int id);

    void update(Restaurant restaurant);

    List<Restaurant> getAll();
}
