package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    Dish save(Dish dish);

    void update(Dish dish) throws ReflectiveOperationException;

    void delete(int id) throws NotFoundException;

    Dish get(int id) throws NotFoundException;

    Dish getForMenu(int id, int menuId) throws NotFoundException;

    List<Dish> getAllForMenu(int menuId) throws NotFoundException;

    List<Dish> getAll();
}
