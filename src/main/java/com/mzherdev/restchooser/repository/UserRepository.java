package com.mzherdev.restchooser.repository;

import com.mzherdev.restchooser.model.User;

import java.util.List;

/**
 * Created by mzherdev on 07.06.2016.
 */
public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
