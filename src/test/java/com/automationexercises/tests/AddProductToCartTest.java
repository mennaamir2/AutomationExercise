package com.automationexercises.tests;

import com.automationexercises.pages.HomePage;
import com.automationexercises.pages.ProductsPage;
import com.automationexercises.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductToCartTest extends BaseTest {

    @Test(description = "Verify that user can add product to cart and view it")
    public void testAddProductToCart() {
        HomePage homePage = new HomePage(getWebDriver());
        Assert.assertTrue(homePage.isLogoDisplayed(), "Home page logo is not displayed!");

        ProductsPage productsPage = homePage.clickOnProductsMenu();
        Assert.assertTrue(productsPage.isAllProductsTitleDisplayed(), "All Products title is not displayed!");

        productsPage.addFirstProductToCart();
        CartPage cartPage = productsPage.clickViewCart();

        Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty!");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}