package com.company;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class EventListener implements WebDriverListener {

    // JS executor
    // Listeners: WebDriverListener, EventFiringDecorator
    public static void eventListenerTesting() throws InterruptedException {
        String chromeDriverLoc = "/Users/brandonlin/Documents/Projects/ExternalLibraries/SeleniumDrivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLoc);
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // setup listener
        WebDriverListener listener = new EventListener();
        WebDriver decorated =  new EventFiringDecorator(listener).decorate(driver);

        decorated.get("https://www.amazon.ca");
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0, 400)");
        Thread.sleep(2000);

        decorated.quit();
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        System.out.println("url: " + url);
        WebDriverListener.super.afterGet(driver, url);
    }
}
