package com.automationexercises.tests;

import com.automationexercises.pages.HomePage;
import com.automationexercises.pages.ProductsPage;
import com.automationexercises.pages.CartPage;
import com.automationexercises.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(description = "Verify Guest User can Checkout")
    public void testCheckoutFlow() {
        HomePage homePage = new HomePage(getWebDriver());
        Assert.assertTrue(homePage.isLogoDisplayed(), "Home page logo is not displayed!");

        ProductsPage productsPage = homePage.clickOnProductsMenu();
        productsPage.addFirstProductToCart();

        CartPage cartPage = productsPage.clickViewCart();
        Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty!");
        cartPage.clickProceedToCheckout();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}