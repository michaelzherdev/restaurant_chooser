package com.mzherdev.restchooser;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {

        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml", "spring/spring-mvc.xml");
            appCtx.refresh();
            System.out.println("BeanDefinitionNames: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

        }
    }
}