package com.automationexercises.tests;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.drivers.WebDriverProvider;
import com.automationexercises.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;

    @BeforeClass
    public void setupClass() {
    }

    @BeforeMethod
    public void setupDriver() {
        driver = new GUIDriver();
        getWebDriver().manage().window().maximize();
        getWebDriver().get("https://automationexercise.com");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                getWebDriver().manage().deleteAllCookies();
            } catch (Exception e) {
                System.out.println("Could not delete cookies: " + e.getMessage());
            } finally {
                getWebDriver().quit();
            }
        }
    }

    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}