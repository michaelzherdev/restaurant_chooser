package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.util.List;

public interface MenuService {

    Menu save(Menu menu, int restaurantId);

    void update(Menu menu) throws ReflectiveOperationException;

    Menu get(int id) throws NotFoundException;

    Menu getOneForRestaurant(int id, int restaurantId) throws NotFoundException;

    List<Menu> getAllForRestaurant(int restaurantId);

    List<Menu> getActuals();

    List<Menu> getAll();
}
