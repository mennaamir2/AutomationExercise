package com.automationexercises.tests;

import com.automationexercises.pages.HomePage;
import com.automationexercises.pages.ProductsPage;
import com.automationexercises.tests.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductTest extends BaseTest {

    @Test(description = "Verify that user can search for a product successfully")
    public void testSearchProduct() {
        HomePage homePage = new HomePage(getWebDriver());
        Assert.assertTrue(homePage.isLogoDisplayed(), "Home page logo is not displayed!");

        ProductsPage productsPage = homePage.clickOnProductsMenu();
        Assert.assertTrue(productsPage.isAllProductsTitleDisplayed(), "All Products title is not displayed!");

        productsPage.searchForProduct("dress");

        Assert.assertTrue(productsPage.areProductsDisplayedAfterSearch(), "No products found after search!");

        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("window.scrollBy(0,400)");

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}