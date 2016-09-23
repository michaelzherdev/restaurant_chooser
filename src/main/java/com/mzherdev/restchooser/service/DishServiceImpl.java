package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.repository.DishRepository;
import com.mzherdev.restchooser.util.exception.ExceptionUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
@Service("dishService")
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository repository;

    @Override
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public void update(Dish dish) {
        repository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public Dish getForMenu(int id, int menuId) {
        return repository.getForMenu(id, menuId);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }
}
