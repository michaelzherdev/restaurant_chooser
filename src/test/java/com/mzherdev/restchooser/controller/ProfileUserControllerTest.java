package com.mzherdev.restchooser.controller;

import com.mzherdev.restchooser.TestUtil;
import com.mzherdev.restchooser.model.Role;
import com.mzherdev.restchooser.model.User;
import com.mzherdev.restchooser.util.json.JsonUtil;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Collections;

import static com.mzherdev.restchooser.data.UserTestData.*;
import static com.mzherdev.restchooser.web.user.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileUserControllerTest extends AbstractControllerTest{

    @Test
    public void getUser() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(TestUtil.userHttpBasic(USER, USER_PASS)))
                .andExpect(status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER));
    }

    @Test
    public void getUnauthorized() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(TestUtil.userHttpBasic(USER, USER_PASS)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), userService.getAll());
    }

    @Test
    public void update() throws Exception {
        User updated = new User(0, "newName", "newemail@ya.ru", "newPassword", Role.ROLE_USER);

        mockMvc.perform(put(REST_URL).contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .with(TestUtil.userHttpBasic(USER, USER_PASS))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
