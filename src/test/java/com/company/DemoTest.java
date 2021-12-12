package com.company;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;


import static org.junit.jupiter.api.Assertions.*;

@Listeners(ListenerTest.class)

public class DemoTest {

    static WebDriver driver;

    @BeforeClass
    public static void browserOpen() {
        String chromeDriverLoc = "/Users/brandonlin/Documents/Projects/ExternalLibraries/SeleniumDrivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLoc);
        driver = new ChromeDriver();
    }

    @Test
    public void getGoogle() {
        driver.get("https://www.google.ca");
        String title = driver.getTitle();

        String expectedTitle = "Google";
        assertEquals(expectedTitle, title);
    }

    @Test
    public void failTest() {
        Assert.fail();
    }

    @Ignore
    public void ignoreTest() {

    }

    @AfterClass
    public static void browserClose() {
        driver.quit();
    }
}