package com.mzherdev.restchooser.web.menu;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mzherdev on 21.09.2016.
 */
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

    public Menu getForRestaurant(int id, int restaurantId) {
        log.info("getForMenu " + id);
        return service.getForRestaurant(id, restaurantId);
    }

    public Menu create(Menu menu) {
        menu.setId(null);
        log.info("create " + menu);
        return service.save(menu);
    }

    public void update(Menu menu, int id) {
        menu.setId(id);
        log.info("update " + menu);
        service.update(menu);
    }

    public void update(Menu menu) {
        log.info("update " + menu);
        service.update(menu);
    }


}
