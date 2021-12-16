package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementBasics {

    /**
     * A function where I practiced basic WebElement functions and concepts
            // implicit waits
            // Selectors: id, cssSelector, XPath, linkText
            // manage(), .window().maximize(), .deleteAllCookies(), .timeouts().pageLoadTimeout(), .timeouts().implicitlyWait()
            // thread.sleep(), sendKeys(), click(), navigate.to(), navigate.back(), driver.quit()
     */
    public static void webElementBasics() throws InterruptedException {
        String chromeDriverLoc = "/Users/brandonlin/Documents/Projects/ExternalLibraries/SeleniumDrivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLoc);
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get("https://www.amazon.ca/");
        Thread.sleep(2000);
        WebElement searchTextBox = driver.findElement(By.id("twotabsearchtextbox"));
        String searchKey = "bruh bruh";
        sendKeys(driver, searchTextBox, Duration.ofSeconds(10), searchKey);

        Thread.sleep(2000);
        WebElement submitSearchButton = driver.findElement(By.id("nav-search-submit-button"));
        submitSearchButton.click();

        Thread.sleep(2000);
        WebElement checkBoxText = driver.findElement(By.linkText("Wet Brush"));
        checkBoxText.click();

        Thread.sleep(2000);
        driver.navigate().to("https://www.google.com");
        String googleTitle = driver.getTitle();
        driver.findElement(By.xpath("//a[@class = 'MV3Tnb']")).click();

        Thread.sleep(2000);
        driver.navigate().back();

        Thread.sleep(2000);
        driver.quit();

        String expectedTitle = "Google";
        if (googleTitle.equals(expectedTitle)) {
            System.out.println("Test Successful");
        } else {
            System.out.println("Test Failure");
        }
    }

    /** sendKeys with built-in WebDriverWait for element visibility */
    public static void sendKeys(WebDriver driver, WebElement element, Duration timeout, String text) {
        new WebDriverWait(driver, timeout).until(
                ExpectedConditions.visibilityOf( element));
        element.sendKeys(text);
    }
}
