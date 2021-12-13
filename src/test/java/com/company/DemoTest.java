package com.company;

import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Listeners(ListenerTest.class)

public class DemoTest {

    static WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void browserOpen(String browser) throws Exception {
        if (browser.equalsIgnoreCase("Chrome")) {
            String chromeDriverLoc = "/Users/brandonlin/Documents/Projects/ExternalLibraries/SeleniumDrivers/chromedriver";

            System.setProperty("webdriver.chrome.driver", chromeDriverLoc);
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Safari")) {
            driver = new SafariDriver();
        } else {
            throw new Exception("Invalid Browser");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void getGoogle() throws InterruptedException {
        driver.get("https://www.google.ca");
        String title = driver.getTitle();

        Thread.sleep(2000);

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
    public void browserClose() {
        driver.quit();
    }
}