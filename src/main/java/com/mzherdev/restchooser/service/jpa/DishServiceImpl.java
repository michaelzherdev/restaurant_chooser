package com.mzherdev.restchooser.service.jpa;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.repository.DishRepository;
import com.mzherdev.restchooser.service.DishService;
import com.mzherdev.restchooser.util.JpaUtil;
import com.mzherdev.restchooser.util.ValidationUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dishService")
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository repository;

    @Override
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public void update(Dish dish) throws ReflectiveOperationException {
        Dish updatable  = get(dish.getId());
        dish = JpaUtil.updateEntity(updatable, dish);
        repository.save(dish);
    }

    @Override
    public void delete(int id) {
        ValidationUtil.check(repository.delete(id), id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return ValidationUtil.check(repository.get(id), id);
    }

    @Override
    public Dish getForMenu(int id, int menuId) {
        return ValidationUtil.check(repository.getForMenu(id, menuId), id);
    }

    @Override
    public List<Dish> getAllForMenu(int menuId) throws NotFoundException {
        return repository.getAllForMenu(menuId);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }
}
