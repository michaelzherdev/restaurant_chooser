package com.mzherdev.restchooser.web.dish;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.service.DishService;
import com.mzherdev.restchooser.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public List<Dish> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Dish get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public Dish getForMenu(int id, int menuId) {
        log.info("getForMenu " + id);
        return service.getForMenu(id, menuId);
    }

    public List<Dish> getAllForMenu(int menuId) {
        log.info("getAllForMenu " + menuId);
        return service.getAllForMenu(menuId);
    }

    public Dish create(Dish dish) {
        dish.setId(null);
        log.info("create " + dish);
        return service.save(dish);
    }

    public void update(Dish dish, int id) throws ReflectiveOperationException {
        ValidationUtil.assureIdConsistent(dish, id);
        log.info("update " + dish);
        service.update(dish);
    }

    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }
}
