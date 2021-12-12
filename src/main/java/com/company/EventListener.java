package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class EventListener implements WebDriverListener {

    @Override
    public void afterGet(WebDriver driver, String url) {
        System.out.println("url: " + url);
        WebDriverListener.super.afterGet(driver, url);
    }
}
