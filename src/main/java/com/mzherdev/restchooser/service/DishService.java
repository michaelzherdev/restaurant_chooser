package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
public interface DishService {

    Dish save(Dish dish);

    void update(Dish dish);

    boolean delete(int id);

    Dish get(int id) throws NotFoundException;

    Dish getForMenu(int id, int menuId);

    List<Dish> getAll();
}
