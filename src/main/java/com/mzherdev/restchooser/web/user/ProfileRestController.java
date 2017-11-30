package com.mzherdev.restchooser.web.user;

import com.mzherdev.restchooser.AuthorizedUser;
import com.mzherdev.restchooser.model.User;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    public static final String REST_URL = "/profile";

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) throws ReflectiveOperationException {
        user.setId(AuthorizedUser.id());
        super.update(user);
    }
}