package com.mzherdev.restchooser.controller;

import com.mzherdev.restchooser.web.restaurant.RestaurantRestController;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.mzherdev.restchooser.TestUtil.authorize;
import static com.mzherdev.restchooser.data.RestaurantTestData.*;
import static com.mzherdev.restchooser.data.UserTestData.*;
import static com.mzherdev.restchooser.web.user.AdminRestController.REST_URL;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootUserControllerTest extends AbstractControllerTest {

    @Test
    public void userList() throws Exception {
        authorize(ADMIN, ADMIN_PASS);
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void userListUnAuth() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getRestaurantList() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(RestaurantRestController.RESTAURANT_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantRestControllerTest.MATCHER.contentListMatcher(RESTAURANT_2, RESTAURANT_1, RESTAURANT_3));
    }

    @Test
    public void root() throws Exception {
        mockMvc.perform(formLogin("/spring_security_check").user(USER.getEmail()).password(USER_PASS))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));
    }
}