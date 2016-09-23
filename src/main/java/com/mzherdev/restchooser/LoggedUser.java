package com.mzherdev.restchooser;

import com.mzherdev.restchooser.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

/**
 * Created by mzherdev on 07.06.2016.
 */

public class LoggedUser extends org.springframework.security.core.userdetails.User {
    private static User user;
    private static int id = 1000; // to change need to go on login page

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = user;
    }

    public static int id() {
        return id;
    }

//    public static User get() {
//        return user;
//    }

    public void update(User newUser) {
        user = newUser;
    }

    public static void setUser(User user) {
        LoggedUser.user = user;
    }

    public static void setId(int id) {
        LoggedUser.id = id;
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
