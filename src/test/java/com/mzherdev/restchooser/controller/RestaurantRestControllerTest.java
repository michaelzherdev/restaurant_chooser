package com.mzherdev.restchooser.controller;

import com.mzherdev.restchooser.TestUtil;
import com.mzherdev.restchooser.matcher.ModelMatcher;
import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.service.RestaurantService;
import com.mzherdev.restchooser.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.function.Function;

import static com.mzherdev.restchooser.TestUtil.authorize;
import static com.mzherdev.restchooser.TestUtil.userHttpBasic;
import static com.mzherdev.restchooser.data.RestaurantTestData.*;
import static com.mzherdev.restchooser.data.RestaurantTestData.assertMatch;
import static com.mzherdev.restchooser.data.UserTestData.*;
import static com.mzherdev.restchooser.web.restaurant.RestaurantRestController.RESTAURANT_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = RESTAURANT_URL + '/';

    public static final ModelMatcher<Restaurant, Restaurant> MATCHER = new ModelMatcher<>(Function.identity(), Restaurant.class);

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void getAll() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(USER, USER_PASS)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(RESTAURANT_1));
    }

    @Test
    public void getNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(USER, USER_PASS)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void getBestOfTheDay() throws Exception {
        mockMvc.perform(get(REST_URL + "best/2017-12-01")
                .with(userHttpBasic(USER, USER_PASS)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(RESTAURANT_2));
    }

    @Test
    public void getBestOfCurrentDay() throws Exception {
        mockMvc.perform(get(REST_URL + "best")
                .with(userHttpBasic(USER, USER_PASS)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void create() throws Exception {
        Restaurant expected = new Restaurant("NEW", "the best restaurant ever.");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN, ADMIN_PASS))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        assertMatch(expected, returned);
    }

    @Test
    public void deleteRestaurant() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_3_ID)
                .with(TestUtil.userHttpBasic(ADMIN, ADMIN_PASS)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_2, RESTAURANT_1), restaurantService.getAll());
    }

    @Test
    public void deleteForbidden() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_3_ID)
                .with(TestUtil.userHttpBasic(USER, USER_PASS)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_3);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT_3_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN, ADMIN_PASS))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, restaurantService.get(RESTAURANT_3_ID));
    }

    @Test
    public void updateForbidden() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_3);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT_3_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER, USER_PASS))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void updateUnauthorizedFailed() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_3);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT_3_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER, ""))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isUnauthorized());
    }

}