package com.mzherdev.restchooser.controller;

import com.mzherdev.restchooser.TestUtil;
import com.mzherdev.restchooser.matcher.ModelMatcher;
import com.mzherdev.restchooser.model.Dish;
import com.mzherdev.restchooser.service.DishService;
import com.mzherdev.restchooser.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.function.Function;

import static com.mzherdev.restchooser.TestUtil.authorize;
import static com.mzherdev.restchooser.TestUtil.userHttpBasic;
import static com.mzherdev.restchooser.data.DishTestData.*;
import static com.mzherdev.restchooser.data.DishTestData.assertMatch;
import static com.mzherdev.restchooser.data.MenuTestData.MENU_1_ID;
import static com.mzherdev.restchooser.data.UserTestData.*;
import static com.mzherdev.restchooser.web.dish.DishRestController.DISH_URL;
import static com.mzherdev.restchooser.web.menu.MenuRestController.MENU_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DISH_URL + '/';
    private static final String REST_URL_WITH_MENU = MENU_URL + "/" + MENU_1_ID + DISH_URL + '/';

    private static final ModelMatcher<Dish, Dish> MATCHER = new ModelMatcher<>(Function.identity(), Dish.class);

    @Autowired
    DishService dishService;

    @Test
    public void getAll() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOne() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL + DISH_1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH_1));
    }

    @Test
    public void getNotFound() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL + 1))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getForMenu() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL_WITH_MENU + DISH_1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(DISH_1));
    }

    @Test
    public void getAllForMenu() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL_WITH_MENU))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH_1, DISH_2, DISH_3, DISH_4));
    }

    @Test
    public void createWithLocation() throws Exception {
        Dish dish = new Dish(10.0, "New");
        Dish expected = dishService.save(dish);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN, ADMIN_PASS))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Dish returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        assertMatch(expected, returned);
    }

    @Test
    public void update() throws Exception {
        Dish updated = new Dish(DISH_1);
        updated.setName("Updated dish");
        mockMvc.perform(put(REST_URL + DISH_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN, ADMIN_PASS))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, dishService.get(DISH_1_ID));
    }

    @Test
    public void deleteOne() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH_12_ID)
                .with(TestUtil.userHttpBasic(ADMIN, ADMIN_PASS)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8,
                DISH_9, DISH_10, DISH_11), dishService.getAll());
    }

}