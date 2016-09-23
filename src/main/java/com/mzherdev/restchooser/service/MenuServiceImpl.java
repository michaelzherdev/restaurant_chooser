package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.repository.MenuRepository;
import com.mzherdev.restchooser.util.exception.ExceptionUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository repository;

    @Override
    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    @Override
    public void update(Menu menu) {
        repository.save(menu);
    }

    @Override
    public Menu get(int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public Menu getForRestaurant(int id, int restaurantId) {
        return repository.getForRestaurant(id, restaurantId);
    }

    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }
}
