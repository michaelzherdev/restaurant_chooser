package com.mzherdev.restchooser.web.menu;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.service.MenuService;
import com.mzherdev.restchooser.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractMenuController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;

    public List<Menu> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Menu get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public Menu getOneForRestaurant(int id, int restaurantId) {
        log.info("getForMenu " + id);
        return service.getOneForRestaurant(id, restaurantId);
    }

    public List<Menu> getActual() {
        log.info("getActual");
        return service.getActuals();
    }

    public List<Menu> getAllForRestaurant(int restaurantId) {
        log.info("getForRestaurant " + restaurantId);
        return service.getAllForRestaurant(restaurantId);
    }

    public Menu create(Menu menu, int restaurantId) {
        menu.setId(null);
        log.info("create " + menu);
        return service.save(menu, restaurantId);
    }

    public void update(Menu menu, int id) throws ReflectiveOperationException {
        ValidationUtil.assureIdConsistent(menu, id);
        log.info("update " + menu);
        service.update(menu);
    }
}
