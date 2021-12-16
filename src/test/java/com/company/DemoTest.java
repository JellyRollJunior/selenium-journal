package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        WebDriver undecorated;
        if (browser.equalsIgnoreCase("Chrome")) {
            String chromeDriverLoc = "/Users/brandonlin/Documents/Projects/ExternalLibraries/SeleniumDrivers/chromedriver";

            System.setProperty("webdriver.chrome.driver", chromeDriverLoc);
            undecorated = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Safari")) {
            undecorated = new SafariDriver();
        } else {
            throw new Exception("Invalid Browser");
        }

        WebDriverListener listener = new EventListener();
        driver = new EventFiringDecorator(listener).decorate(undecorated);
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://www.google.ca");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        WebElement searchBar = driver.findElement(By.className("gsfi"));
        WebElement googleSearch = driver.findElement(By.className("gNO89b"));
        Actions builder = new Actions(driver);

        String search = "yohji";
        Action enterSearch = builder.sendKeys(searchBar, search).build();
        enterSearch.perform();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(googleSearch));
        Action clickSearch = builder
                .moveToElement(googleSearch)
                .click()
                .build();
        clickSearch.perform();
        Thread.sleep(2000);

        String expectedTitle = "yohji - Google Search";
        String pageTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, pageTitle);
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 400)");
        Thread.sleep(2000);

        WebElement theShop = driver.findElement(By.xpath("//h3[contains(text(), 'THE SHOP YOHJI')]"));
        Action enterShop = builder
                .moveToElement(theShop)
                .click()
                .build();
        enterShop.perform();

        // SAFARI LOADS SUPER SLOW SO I NEED THE SLEEP BEFORE GET TITLE
        expectedTitle = "THE SHOP YOHJI YAMAMOTO";
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs(expectedTitle));
        pageTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, pageTitle);
        Thread.sleep(3000);
    }

    @Test
    public void cssSelectorPractice() {

    }

    @Test
    public void xPathPractice() {

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