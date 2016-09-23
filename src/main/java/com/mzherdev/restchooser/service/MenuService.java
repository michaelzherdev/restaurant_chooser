package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
public interface MenuService {

    Menu save(Menu menu);

    void update(Menu menu);

//    boolean delete(int id);

    Menu get(int id) throws NotFoundException;

    Menu getForRestaurant(int id, int restaurantId);

    List<Menu> getAll();
}
