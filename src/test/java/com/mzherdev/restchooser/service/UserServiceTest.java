package com.mzherdev.restchooser.service;

import com.mzherdev.restchooser.model.Role;
import com.mzherdev.restchooser.model.User;
import com.mzherdev.restchooser.util.PasswordUtil;
import com.mzherdev.restchooser.util.UserUtil;
import com.mzherdev.restchooser.util.exception.NotFoundException;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.mzherdev.restchooser.data.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @After
    public void tearDown() throws Exception {
        service.evictCache();
    }

    @Test
    public void save() throws Exception {
        User user = new User(null, "New", "new@gmail.com", "newPass", false, Collections.singleton(Role.ROLE_USER));
        user = UserUtil.prepareToSave(user);
        User created = service.save(user);
        user.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, created, USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailSave() throws Exception {
        service.save(new User(null, "Duplicate", "user@yandex.ru", PasswordUtil.encode("newPass"), Role.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(ADMIN_ID);
        assertMatch(ADMIN, user);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(USER, user);
    }

    @Test
    public void getAll() throws Exception {
        Collection<User> all = service.getAll();
        assertMatch(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(updated, service.get(USER_ID));
    }

}