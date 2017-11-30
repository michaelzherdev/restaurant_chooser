package com.mzherdev.restchooser.web.user;

import com.mzherdev.restchooser.model.User;
import com.mzherdev.restchooser.service.UserService;
import com.mzherdev.restchooser.util.UserUtil;
import com.mzherdev.restchooser.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        log.info("create " + user);
        return service.save(UserUtil.prepareToSave(user));
    }

    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) throws ReflectiveOperationException {
        ValidationUtil.assureIdConsistent(user, id);
        log.info("update " + user);
        service.update(user);
    }

    public void update(User user) throws ReflectiveOperationException {
        log.info("update " + user);
        service.update(UserUtil.prepareToSave(user));
    }

    public User getByMail(String email) {
        log.info("getByEmail " + email);
        return service.getByEmail(email);
    }
}
