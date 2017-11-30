package com.mzherdev.restchooser.data;

import com.mzherdev.restchooser.model.Vote;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.mzherdev.restchooser.data.RestaurantTestData.RESTAURANT_2;
import static com.mzherdev.restchooser.data.UserTestData.USER;
import static com.mzherdev.restchooser.model.User.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {

    public static final int VOTE_ID = START_SEQ;

    public static final Vote VOTE = new Vote(VOTE_ID, LocalDateTime.of(2017, 12, 1, 10, 0, 0), USER, RESTAURANT_2);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "voteTime", "restaurant", "user");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual)
                .usingElementComparatorIgnoringFields("voteTime", "restaurant", "user")
                .isEqualTo(expected);
    }
}
