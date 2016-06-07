package com.mzherdev.restchooser;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by mzherdev on 07.06.2016.
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml");
            appCtx.refresh();

            System.out.println("LOOK HERE: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
//            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
//            System.out.println(adminUserController.get(UserTestData.USER_ID));
            System.out.println();

//            AbstractUserMealController mealController = appCtx.getBean(UserMealRestController.class);
//            List<UserMealWithExceed> filteredMealsWithExceeded =
//                    mealController.getBetween(
//                            LocalDate.of(2015, Month.MAY, 30), LocalTime.of(7, 0),
//                            LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0));
//            filteredMealsWithExceeded.forEach(System.out::println);
        }
    }
}