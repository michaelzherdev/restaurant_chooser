package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.mzherdev.restchooser.data.MenuTestData.*;
import static com.mzherdev.restchooser.data.MenuTestData.assertMatch;
import static com.mzherdev.restchooser.data.RestaurantTestData.*;

public class MenuServiceTest extends AbstractServiceTest{

    @Autowired
    MenuService service;

    @Test
    public void save() throws Exception {
        Menu menu = new Menu(LocalDate.of(2017, 12, 2));
        Menu created = service.save(menu, RESTAURANT_1_ID);
        menu.setId(created.getId());
        assertMatch(service.getAll(), MENU_1, MENU_2, MENU_3, MENU_4, created);
    }

    @Test
    public void update() throws Exception {
        Menu updated = new Menu(MENU_3);
        updated.setDay(LocalDate.now());
        service.update(updated);
        assertMatch(updated, service.get(MENU_3_ID));
    }

    @Test
    public void get() throws Exception {
        Menu menu = service.get(MENU_1_ID);
        assertMatch(menu, MENU_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        Menu menu = service.get(1);
    }

    @Test
    public void getOneForRestaurant() throws Exception {
        Menu menu = service.getOneForRestaurant(MENU_1_ID, RESTAURANT_1_ID);
        assertMatch(menu, MENU_1);
    }

    @Test
    public void getAllForRestaurant() throws Exception {
        List<Menu> menus = service.getAllForRestaurant(RESTAURANT_2_ID);
        assertMatch(menus, MENU_2);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(), MENU_1, MENU_2, MENU_3, MENU_4);
    }

    @Test
    public void getActuals() throws Exception {
        assertMatch(service.getActuals(), MENU_2, MENU_3, MENU_4);
    }

}