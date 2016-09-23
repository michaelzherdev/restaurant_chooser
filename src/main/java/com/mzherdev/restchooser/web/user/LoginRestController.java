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
@RequestMapping(LoginRestController.REST_URL)
public class LoginRestController extends AbstractUserController implements ExceptionInfoHandler {
    public static final String REST_URL = "/rest/login";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByMail(@RequestParam("email") String email) {
        User user = super.getByMail(email);
        LoggedUser.setUser(user);
        LoggedUser.setId(user.getId());
        return user;
    }
}