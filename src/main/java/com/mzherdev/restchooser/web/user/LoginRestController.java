package com.mzherdev.restchooser.web.user;

import com.mzherdev.restchooser.AuthorizedUser;
import com.mzherdev.restchooser.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Objects;

@Scope("session")
@RestController
@RequestMapping(LoginRestController.REST_URL)
public class LoginRestController extends AbstractUserController {
    public static final String REST_URL = "/login";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByMail(HttpServletRequest request){//@RequestParam("email") String email) {
        String authorization = request.getHeader("authorization");
        if (authorization != null && authorization.startsWith("Basic")) {
            String base64Credentials = authorization.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                    Charset.forName("UTF-8"));
            String email = credentials.split(":")[0];
            User user = super.getByMail(email);
            if (Objects.isNull(user)) {
                throw new UsernameNotFoundException("User " + email + " is not found");
            }
            new AuthorizedUser(user);
            return user;
        }
        return null;
    }
}