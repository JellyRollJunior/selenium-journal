package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ComplexCssSelectorPractice {

    public static void cssSelectorPractice() throws InterruptedException {
        String chromeDriverLoc = "/Users/brandonlin/Documents/Projects/ExternalLibraries/SeleniumDrivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLoc);
        WebDriver driver = new ChromeDriver();
        Actions builder = new Actions(driver);
        driver.get("https://amazon.ca");

        // click second item in featured deals
        WebElement secondFeaturedBox = driver.findElement(By.cssSelector("#gw-card-layout>:nth-child(3)"));
        Action clickSecond = builder.moveToElement(secondFeaturedBox).click().build();
        clickSecond.perform();

        // click fifth checkbox in departments section
        String cssDepartmentFifth = "span[aria-label='Departments filter'] div[class*='CheckboxFilter']:nth-child(5)";
        WebElement departmentsFifthCheckBox = driver.findElement(By.cssSelector(cssDepartmentFifth));
        Action clickDepartmentFifthCheckBox = builder.moveToElement(departmentsFifthCheckBox).click().build();
        clickDepartmentFifthCheckBox.perform();
        Thread.sleep(2000);

        // click last deal in deals grid
        String cssDealGridLast = "[class*='Grid-module__grid_1'] [class*='DealGrid'] + :last-child";
        WebElement dealGridLast = driver.findElement(By.cssSelector(cssDealGridLast));
        Action clickDealGridLast = builder.moveToElement(dealGridLast).click().build();
        clickDealGridLast.perform();
        Thread.sleep(2000);
    }
}
