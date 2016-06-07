package com.mzherdev.restchooser;

import com.mzherdev.restchooser.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

/**
 * Created by mzherdev on 07.06.2016.
 */

public class LoggedUser {
    private User user;
    private static int id;

    public LoggedUser(User user) {
        this.user = user;
        id = user.getId();
    }

    public static int id() {
        return id;
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

    public void update(User newUser) {
        user = newUser;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
