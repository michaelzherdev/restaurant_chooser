package com.mzherdev.restchooser.data;

import com.mzherdev.restchooser.matcher.ModelMatcher;
import com.mzherdev.restchooser.model.Role;
import com.mzherdev.restchooser.model.User;

import java.util.Arrays;
import java.util.function.Function;

import static com.mzherdev.restchooser.model.User.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "$2a$10$f6OL5eOTCOEgLVxvErO98OPBWgvs8j/no0kze3N9ME7fXCRGI26s.", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "$2a$10$gM/KJ4qlO2yWTVBJcHhV3.TIUsGQV/wGW9mRUL1nEvQzx3vaxHVTS", Role.ROLE_ADMIN);

    public static final String USER_PASS = "user";
    public static final String ADMIN_PASS = "admin";

    public static final ModelMatcher<User, User> MATCHER = new ModelMatcher<>(Function.identity(), User.class);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles", "votes", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual)
//                .usingElementComparatorIgnoringFields("registered", "roles", "votes")
                .usingElementComparatorOnFields("id", "name", "email", "password")
                .isEqualTo(expected);
    }
}
