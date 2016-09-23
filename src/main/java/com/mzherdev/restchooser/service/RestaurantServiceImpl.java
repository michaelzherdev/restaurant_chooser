package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.repository.RestaurantRepository;
import com.mzherdev.restchooser.util.exception.ExceptionUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mzherdev on 21.09.16.
 */
@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.check(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
return ExceptionUtil.check(repository.get(id), id);
    }

    @Override
    public Menu getMenu(int id, int menuId) throws NotFoundException {
        return repository.getMenu(id, menuId);
    }

    @Override
    public List<Menu> getMenus(int id) {
        return repository.getMenus(id);
    }

    @Override
    public void update(Restaurant restaurant) { repository.save(restaurant); }

    @Override
    public List<Restaurant> getAll() { return  repository.getAll(); }
}
