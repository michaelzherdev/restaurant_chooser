package com.mzherdev.restchooser.controller;

import com.mzherdev.restchooser.data.VoteTestData;
import com.mzherdev.restchooser.matcher.ModelMatcher;
import com.mzherdev.restchooser.model.Vote;
import com.mzherdev.restchooser.service.VoteService;
import com.mzherdev.restchooser.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.function.Function;

import static com.mzherdev.restchooser.TestUtil.authorize;
import static com.mzherdev.restchooser.TestUtil.userHttpBasic;
import static com.mzherdev.restchooser.data.UserTestData.*;
import static com.mzherdev.restchooser.data.VoteTestData.VOTE;
import static com.mzherdev.restchooser.data.VoteTestData.VOTE_ID;
import static com.mzherdev.restchooser.web.restaurant.RestaurantRestController.RESTAURANT_URL;
import static com.mzherdev.restchooser.web.vote.VoteRestController.VOTE_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VOTE_URL + '/';
    private static final String REST_URL_WITH_RESTAURANT = RESTAURANT_URL + "/1001" + VOTE_URL + '/';

    private static final ModelMatcher<Vote, Vote> MATCHER = new ModelMatcher<>(Function.identity(), Vote.class);

    @Autowired
    VoteService voteService;

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
        mockMvc.perform(get(REST_URL + VOTE_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(VOTE));
    }

    @Test
    public void getNotFound() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL + 1))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllForRestaurant() throws Exception {
        authorize(USER, USER_PASS);
        mockMvc.perform(get(REST_URL_WITH_RESTAURANT))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateBefore11Am() throws Exception {
        Vote updated = new Vote(VOTE);
        updated.setVoteTime(LocalDateTime.of(2017, 11, 1, 10, 59));
        mockMvc.perform(put(REST_URL_WITH_RESTAURANT + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER, USER_PASS))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, voteService.get(VOTE_ID));
    }

    @Test
    public void updateAfter11Am() throws Exception {
        Vote updated = new Vote(VOTE);
        updated.setVoteTime(LocalDateTime.of(2017, 11, 1, 11, 1));
        mockMvc.perform(put(REST_URL_WITH_RESTAURANT + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER, USER_PASS))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void updateWrongUser() throws Exception {
        Vote updated = new Vote(VOTE);
        updated.setVoteTime(LocalDateTime.of(2017, 11, 1, 10, 00));
        mockMvc.perform(put(REST_URL_WITH_RESTAURANT + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN, ADMIN_PASS))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void createVote() throws Exception {
        Vote expected = new Vote();
        ResultActions action = mockMvc.perform(post(REST_URL_WITH_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER, USER_PASS))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Vote returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        VoteTestData.assertMatch(expected, returned);
    }

    @Test
    public void createVoteAdminFailed() throws Exception {
        Vote expected = new Vote();
        mockMvc.perform(post(REST_URL_WITH_RESTAURANT)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN, ADMIN_PASS))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isForbidden());
    }

}