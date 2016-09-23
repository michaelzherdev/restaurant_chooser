package com.mzherdev.restchooser.repository;

import com.mzherdev.restchooser.model.Menu;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
public interface MenuRepository {

    Menu save(Menu dish);

    Menu get(int id);

    Menu getForRestaurant(int id, int restaurantId);

    List<Menu> getAll();
}
