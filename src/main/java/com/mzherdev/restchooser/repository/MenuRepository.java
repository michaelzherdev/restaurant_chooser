package com.mzherdev.restchooser.repository;

import com.mzherdev.restchooser.model.Menu;

import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restaurantId);

    Menu update(Menu menu);

    Menu get(int id);

    Menu getOneForRestaurant(int id, int restaurantId);

    List<Menu> getAllForRestaurant(int restaurantId);

    List<Menu> getActuals();

    List<Menu> getAll();
}
