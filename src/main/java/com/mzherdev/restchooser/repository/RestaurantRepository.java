package com.mzherdev.restchooser.repository;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.model.Restaurant;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    Menu getMenu(int id, int menuId);

    List<Menu> getMenus(int id);

    List<Restaurant> getAll();
}
