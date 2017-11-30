package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mzherdev.restchooser.data.DishTestData.*;
import static com.mzherdev.restchooser.data.MenuTestData.MENU_1_ID;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    DishService service;

    @Test
    public void save() throws Exception {
        Dish dish = new Dish(10.0, "New");
        Dish created = service.save(dish);
        dish.setId(created.getId());
        assertMatch(service.getAll(), DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8,
                DISH_9, DISH_10, DISH_11, DISH_12, created);
    }

    @Test
    public void update() throws Exception {
        Dish updated = new Dish(DISH_1);
        updated.setName("Updated dish");
        service.update(updated);
        assertMatch(updated, service.get(DISH_1_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(DISH_12_ID);
        assertMatch(Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8,
                DISH_9, DISH_10, DISH_11), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        Dish dish = service.get(DISH_1_ID);
        assertMatch(dish, DISH_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getForMenu() throws Exception {
        Dish dish = service.getForMenu(DISH_1_ID, MENU_1_ID);
        assertMatch(dish, DISH_1);
    }

    @Test(expected = NotFoundException.class)
    public void getForMenuNotFound() throws Exception {
        service.getForMenu(DISH_6_ID, MENU_1_ID);
    }

    @Test
    public void getAllForMenu() throws Exception {
        List<Dish> dishes = service.getAllForMenu(MENU_1_ID);
        assertMatch(dishes, DISH_1, DISH_2, DISH_3, DISH_4);
    }

    @Test
    public void getAllForMenuNotFound() throws Exception {
        List<Dish> dishes = service.getAllForMenu(1);
        assertMatch(dishes, Collections.emptyList());
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(), DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8,
                DISH_9, DISH_10, DISH_11, DISH_12);
    }

}