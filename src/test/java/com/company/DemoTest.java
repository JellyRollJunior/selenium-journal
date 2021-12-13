package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import org.junit.*;
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
    public void getYohji() throws InterruptedException {
        driver.get("https://www.google.ca");
        Thread.sleep(1000);

        WebElement searchBar = driver.findElement(By.xpath("//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]"));
        WebElement googleSearch = driver.findElement(By.cssSelector("input.gNO89b"));
        Actions builder = new Actions(driver);
        Action searchYohji = builder
                .sendKeys(searchBar, "yohji")
                .keyDown(googleSearch, Keys.SHIFT)
                .keyUp(googleSearch, Keys.SHIFT)
                .build();
        searchYohji.perform();

        String expectedTitle = "yohji - Google Search";
        String pageTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, pageTitle);
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 600)");
        Thread.sleep(2000);

        WebElement theShop = driver.findElement(By.xpath("//h3[contains(text(), 'THE SHOP YOHJI')]"));
        Action enterShop = builder
                .moveToElement(theShop)
                .click()
                .build();
        enterShop.perform();

        expectedTitle = "THE SHOP YOHJI YAMAMOTO";
        pageTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, pageTitle);
        Thread.sleep(3000);
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