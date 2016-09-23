package com.mzherdev.restchooser.web.user;

import com.mzherdev.restchooser.LoggedUser;
import com.mzherdev.restchooser.model.User;
import com.mzherdev.restchooser.web.ExceptionInfoHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mzherdev on 07.06.2016.
 */
@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController implements ExceptionInfoHandler {
    public static final String REST_URL = "/rest/profile";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(LoggedUser.id());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        super.delete(LoggedUser.id());
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        user.setId(LoggedUser.id());
        super.update(user);
    }
}