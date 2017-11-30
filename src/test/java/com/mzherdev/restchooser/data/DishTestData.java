package com.mzherdev.restchooser.data;

import com.mzherdev.restchooser.model.Dish;

import java.util.Arrays;

import static com.mzherdev.restchooser.data.MenuTestData.*;
import static com.mzherdev.restchooser.model.User.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class DishTestData {

    public static final int DISH_1_ID = START_SEQ;
    public static final int DISH_2_ID = START_SEQ + 1;
    public static final int DISH_3_ID = START_SEQ + 2;
    public static final int DISH_4_ID = START_SEQ + 3;
    public static final int DISH_5_ID = START_SEQ + 4;
    public static final int DISH_6_ID = START_SEQ + 5;
    public static final int DISH_7_ID = START_SEQ + 6;
    public static final int DISH_8_ID = START_SEQ + 7;
    public static final int DISH_9_ID = START_SEQ + 8;
    public static final int DISH_10_ID = START_SEQ + 9;
    public static final int DISH_11_ID = START_SEQ + 10;
    public static final int DISH_12_ID = START_SEQ + 11;


    public static final Dish DISH_1 = new Dish(DISH_1_ID, 2.45, "Hamburger");
    public static final Dish DISH_2 = new Dish(DISH_2_ID, 3.5, "Lasagna");
    public static final Dish DISH_3 = new Dish(DISH_3_ID, 3.0, "Gazpacho");
    public static final Dish DISH_4 = new Dish(DISH_4_ID, 1.5, "Spaghetti");
    public static final Dish DISH_5 = new Dish(DISH_5_ID, 1.5, "Baked pudding");
    public static final Dish DISH_6 = new Dish(DISH_6_ID, 1.75, "Fish soup");
    public static final Dish DISH_7 = new Dish(DISH_7_ID, 1.7, "Pork chop");
    public static final Dish DISH_8 = new Dish(DISH_8_ID, 2.5, "Kebab");
    public static final Dish DISH_9 = new Dish(DISH_9_ID, 3.25, "Cheburek");
    public static final Dish DISH_10 = new Dish(DISH_10_ID, 3.75, "Kharcho soup");
    public static final Dish DISH_11 = new Dish(DISH_11_ID, 3.0, "Pizza");
    public static final Dish DISH_12 = new Dish(DISH_12_ID, 2.0, "Solyanka");

    static {
        DISH_1.setMenu(Arrays.asList(MENU_1));
        DISH_2.setMenu(Arrays.asList(MENU_1, MENU_2));
        DISH_3.setMenu(Arrays.asList(MENU_1));
        DISH_4.setMenu(Arrays.asList(MENU_1));
        DISH_5.setMenu(Arrays.asList(MENU_2));
        DISH_6.setMenu(Arrays.asList(MENU_2));
        DISH_7.setMenu(Arrays.asList(MENU_2));
        DISH_8.setMenu(Arrays.asList(MENU_2));
        DISH_9.setMenu(Arrays.asList(MENU_3));
        DISH_10.setMenu(Arrays.asList(MENU_3));
        DISH_11.setMenu(Arrays.asList(MENU_3));
        DISH_12.setMenu(Arrays.asList(MENU_3));
    }


    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu", "price");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual)
                .usingElementComparatorIgnoringFields("menu", "price")
                .isEqualTo(expected);
    }
}
