package com.mzherdev.restchooser;

import com.mzherdev.restchooser.util.PasswordUtil;

import java.util.Arrays;

public class PasswordEncoder {

    public static void main(String[] args) {
        String adminPassword = PasswordUtil.encode("admin");
        String userPassword = PasswordUtil.encode("user");
        String user2Password = PasswordUtil.encode("qwerty");
        System.out.println(Arrays.asList(adminPassword, userPassword, user2Password));
    }
}
