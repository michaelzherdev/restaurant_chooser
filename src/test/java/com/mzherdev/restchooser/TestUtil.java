package com.mzherdev.restchooser;

import com.mzherdev.restchooser.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.io.UnsupportedEncodingException;

public class TestUtil {

        public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
            System.out.println(getContent(action));
            return action;
        }

        public static String getContent(ResultActions action) throws UnsupportedEncodingException {
            return action.andReturn().getResponse().getContentAsString();
        }

        public static RequestPostProcessor userHttpBasic(User user, String simplePassword) {
            return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), simplePassword);
        }

        public static void authorize(User user, String password) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), password));
        }
    }