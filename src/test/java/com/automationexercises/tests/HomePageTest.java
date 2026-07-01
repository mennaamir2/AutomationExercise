package com.automationexercises.tests;

import com.automationexercises.pages.HomePage;
import com.automationexercises.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(description = "Verify that Home Page is opened successfully and Logo is displayed")
    public void verifyHomePageLogo() {
        HomePage homePage = new HomePage(getWebDriver());

        Assert.assertTrue(homePage.isLogoDisplayed(), "Error: Home page logo is not displayed!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}