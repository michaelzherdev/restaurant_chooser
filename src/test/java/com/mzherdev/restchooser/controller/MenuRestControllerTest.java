package com.mzherdev.restchooser.controller;

import com.mzherdev.restchooser.data.MenuTestData;
import com.mzherdev.restchooser.matcher.ModelMatcher;
import com.mzherdev.restchooser.model.Menu;
import com.mzherdev.restchooser.service.MenuService;
import com.mzherdev.restchooser.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.function.Function;

import static com.mzherdev.restchooser.TestUtil.authorize;
import static com.mzherdev.restchooser.TestUtil.userHttpBasic;
import static com.mzherdev.restchooser.data.MenuTestData.*;
import static com.mzherdev.restchooser.data.RestaurantTestData.RESTAURANT_1_ID;
import static com.mzherdev.restchooser.data.UserTestData.*;
import static com.mzherdev.restchooser.web.menu.MenuRestController.MENU_URL;
import static com.mzherdev.restchooser.web.restaurant.RestaurantRestController.RESTAURANT_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = MENU_URL + '/';
    private static final String REST_URL_WITH_RESTAURANT = RESTAURANT_URL + "/" + RESTAURANT_1_ID + MENU_URL + '/';

    private static final ModelMatcher<Menu, Menu> MATCHER = new ModelMatcher<>(Function.identity(), Menu.class);

    @Autowired
    MenuService menuService;

    @Test
    public void getAllForRestaurant() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL_WITH_RESTAURANT))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getActuals() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL + "/actual"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOne() throws Exception {
        mockMvc.perform(get(REST_URL_WITH_RESTAURANT + MENU_1_ID)
                .with(userHttpBasic(USER, USER_PASS)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MENU_1));
    }

    @Test
    public void getNotFound() throws Exception {
        mockMvc.perform(get(REST_URL_WITH_RESTAURANT + 1)
                .with(userHttpBasic(USER, USER_PASS)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void update() throws Exception {
        Menu updated = new Menu(MENU_3);
        updated.setDay(LocalDate.now());
        mockMvc.perform(put(REST_URL + MENU_3_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN, ADMIN_PASS))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, menuService.get(MENU_3_ID));
    }

    @Test
    public void createMenu() throws Exception {
        Menu expected = new Menu(LocalDate.of(2017, 12, 2));
        ResultActions action = mockMvc.perform(post(REST_URL_WITH_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN, ADMIN_PASS))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Menu returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MenuTestData.assertMatch(expected, returned);
    }

    @Test
    public void createForbidden() throws Exception {
        Menu expected = new Menu(LocalDate.of(2017, 12, 2));
        mockMvc.perform(post(REST_URL_WITH_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER, USER_PASS))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void createUnauthorized() throws Exception {
        Menu expected = new Menu(LocalDate.of(2017, 12, 2));
        mockMvc.perform(post(REST_URL_WITH_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER, ""))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isUnauthorized());
    }

}