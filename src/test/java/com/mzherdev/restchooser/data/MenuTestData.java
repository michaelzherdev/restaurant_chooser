package com.mzherdev.restchooser.data;

import com.mzherdev.restchooser.model.Menu;

import java.time.LocalDate;
import java.util.Arrays;

import static com.mzherdev.restchooser.data.RestaurantTestData.*;
import static com.mzherdev.restchooser.model.User.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    public static final int MENU_1_ID = START_SEQ;
    public static final int MENU_2_ID = START_SEQ + 1;
    public static final int MENU_3_ID = START_SEQ + 2;
    public static final int MENU_4_ID = START_SEQ + 3;

    public static final Menu MENU_1 = new Menu(MENU_1_ID, LocalDate.of(2017, 11, 30));
    public static final Menu MENU_2 = new Menu(MENU_2_ID, LocalDate.of(2017, 12, 1));
    public static final Menu MENU_3 = new Menu(MENU_3_ID, LocalDate.of(2017, 12, 1));
    public static final Menu MENU_4 = new Menu(MENU_4_ID, LocalDate.of(2017, 12, 1));

    static {
        MENU_1.setRestaurant(RESTAURANT_1);
        MENU_2.setRestaurant(RESTAURANT_2);
        MENU_3.setRestaurant(RESTAURANT_3);
        MENU_4.setRestaurant(RESTAURANT_1);
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "restaurant");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorOnFields("id", "day")
                .isEqualTo(expected);
    }
}
