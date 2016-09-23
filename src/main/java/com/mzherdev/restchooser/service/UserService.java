package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.User;
import com.mzherdev.restchooser.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by mzherdev on 07.06.2016.
 */
public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    void enable(int id, boolean enable);

    void evictCache();

}
