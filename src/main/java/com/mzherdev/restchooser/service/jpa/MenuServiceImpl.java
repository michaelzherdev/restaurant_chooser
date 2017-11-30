package com.mzherdev.restchooser.service.jpa;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.repository.MenuRepository;
import com.mzherdev.restchooser.service.MenuService;
import com.mzherdev.restchooser.util.JpaUtil;
import com.mzherdev.restchooser.util.ValidationUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository repository;

    @Override
    public Menu save(Menu menu, int restaurantId) {
        return repository.save(menu, restaurantId);
    }

    @Override
    public void update(Menu menu) throws ReflectiveOperationException {
        Menu updatable  = get(menu.getId());
        menu = JpaUtil.updateEntity(updatable, menu);
        repository.update(menu);
    }

    @Override
    public Menu get(int id) throws NotFoundException {
        return ValidationUtil.check(repository.get(id), id);
    }

    @Override
    public Menu getOneForRestaurant(int id, int restaurantId) throws NotFoundException {
        return ValidationUtil.check(repository.getOneForRestaurant(id, restaurantId), id);
    }

    @Override
    public List<Menu> getAllForRestaurant(int restaurantId) {
        return repository.getAllForRestaurant(restaurantId);
    }

    @Override
    public List<Menu> getActuals() { return repository.getActuals(); }

    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }
}
