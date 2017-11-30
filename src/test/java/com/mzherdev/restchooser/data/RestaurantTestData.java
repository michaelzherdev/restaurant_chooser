package com.mzherdev.restchooser.data;

import com.mzherdev.restchooser.model.Restaurant;

import java.util.Arrays;

import static com.mzherdev.restchooser.model.User.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final int RESTAURANT_1_ID = START_SEQ;
    public static final int RESTAURANT_2_ID = START_SEQ + 1;
    public static final int RESTAURANT_3_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Luxoria", "A beautiful restaurant with calm and comfortable atmosphere");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Frau Engel", "If you love eat, with us you can do it good. Herzlich willkommen!");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_3_ID, "Slavyanka", "Slavonic cuisine. Taste of Eastern Europe.");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "votes", "menus");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorOnFields("id", "name", "description")
                .isEqualTo(expected);
    }
}
