package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {

    // JS executor
    // Listeners: WebDriverListener, EventFiringDecorator
    private static void eventListenerTesting() throws InterruptedException {
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

    // implicit waits
    // Selectors: id, cssSelector, XPath, linkText
    // manage(), .window().maximize(), .deleteAllCookies(), .timeouts().pageLoadTimeout(), .timeouts().implicitlyWait()
    // thread.sleep(), sendKeys(), click(), navigate.to(), navigate.back(), driver.quit()
    private static void webElementBasics() throws InterruptedException {
        String chromeDriverLoc = "/Users/brandonlin/Documents/Projects/ExternalLibraries/SeleniumDrivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLoc);
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.ca/");

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        System.out.println(driver.getTitle());

        // Basic Web Element Operations
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
//        driver.findElement(By.cssSelector("a.MV3Tnb")).click();
        driver.findElement(By.xpath("//a[@class = 'MV3Tnb']")).click();


        Thread.sleep(2000);
        driver.navigate().back();

        Thread.sleep(2000);
        driver.quit();

        // test case -> I can see how JUnit would be useful here
        String expectedTitle = "Google";
        if (googleTitle.equals(expectedTitle)) {
            System.out.println("Test Successful");
        } else {
            System.out.println("Test Failure");
        }
    }

    // explicit waits
    public static void sendKeys(WebDriver driver, WebElement element, Duration timeout, String text) {
        new WebDriverWait(driver, timeout).until(
                ExpectedConditions.visibilityOf( element));
        element.sendKeys(text);
    }

    public static void main(String[] args) throws InterruptedException {
//        webElementBasics();
        eventListenerTesting();
    }
}
