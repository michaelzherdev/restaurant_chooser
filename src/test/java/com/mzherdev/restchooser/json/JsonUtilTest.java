package com.mzherdev.restchooser.json;

import com.mzherdev.restchooser.controller.RestaurantRestControllerTest;
import com.mzherdev.restchooser.model.Restaurant;
import com.mzherdev.restchooser.util.json.JsonUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.mzherdev.restchooser.data.RestaurantTestData.*;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANT_1);
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        RestaurantRestControllerTest.MATCHER.assertEquals(RESTAURANT_1, restaurant);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3));
        System.out.println(json);
        List<Restaurant> restaurants = JsonUtil.readValues(json, Restaurant.class);
        RestaurantRestControllerTest.MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3), restaurants);
    }
}