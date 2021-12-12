package com.company;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

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

    @AfterClass
    public static void browserClose() {
        driver.quit();
    }
}