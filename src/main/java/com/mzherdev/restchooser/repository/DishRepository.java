package com.mzherdev.restchooser.repository;

import com.mzherdev.restchooser.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    Dish getForMenu(int id, int menuId);

    List<Dish> getAllForMenu(int menuId);

    List<Dish> getAll();
}
