package com.automationexercises.tests;

import com.automationexercises.pages.HomePage;
import com.automationexercises.pages.ProductsPage;
import com.automationexercises.pages.CartPage;
import com.automationexercises.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(priority = 1, description = "Verify that multiple products can be added to cart and totals are correct")
    public void testAddMultipleProductsAndVerifyTotals() {
        HomePage homePage = new HomePage(getWebDriver());
        Assert.assertTrue(homePage.isLogoDisplayed(), "Home page logo is not displayed!");

        ProductsPage productsPage = homePage.clickOnProductsMenu();
        Assert.assertTrue(productsPage.isAllProductsTitleDisplayed(), "Products page title is not displayed!");

        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        productsPage.addSecondProductToCart();
        CartPage cartPage = productsPage.clickViewCart();

        Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty!");

        Assert.assertEquals(cartPage.getFirstProductTotalPrice(), "Rs. 500", "First product total price is incorrect!");
        Assert.assertEquals(cartPage.getSecondProductTotalPrice(), "Rs. 400", "Second product total price is incorrect!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2, description = "Verify that user can remove product from cart successfully")
    public void testRemoveProductFromCart() {
        HomePage homePage = new HomePage(getWebDriver());
        Assert.assertTrue(homePage.isLogoDisplayed(), "Home page logo is not displayed!");

        ProductsPage productsPage = homePage.clickOnProductsMenu();

        productsPage.addFirstProductToCart();
        CartPage cartPage = productsPage.clickViewCart();
        Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty!");

        cartPage.removeFirstProduct();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(), "Empty cart message is not displayed!");
    }
}