package com.automationexercises.tests;

import com.automationexercises.pages.HomePage;
import com.automationexercises.pages.ProductsPage;
import com.automationexercises.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdvancedFlowsTest extends BaseTest {

    @Test(priority = 1, description = "Verify product quantity in cart from details page")
    public void testProductQuantityInCart() {
        HomePage homePage = new HomePage(getWebDriver());
        ProductsPage productsPage = homePage.clickOnProductsMenu();

        productsPage.clickFirstProductViewDetails();
        productsPage.setProductQuantity("4");
        productsPage.clickAddToCartFromDetails();

        CartPage cartPage = productsPage.clickViewCart();
        Assert.assertEquals(cartPage.getProductQuantity(), "4", "Product quantity in cart is incorrect!");
    }

    @Test(priority = 2, description = "Verify adding product from details page")
    public void testAddProductFromDetailsPage() {
        HomePage homePage = new HomePage(getWebDriver());
        ProductsPage productsPage = homePage.clickOnProductsMenu();

        productsPage.clickFirstProductViewDetails();
        productsPage.clickAddToCartFromDetails();

        CartPage cartPage = productsPage.clickViewCart();
        Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty after adding from details!");
    }

    @Test(priority = 3, description = "Verify scroll down to footer and scroll up using arrow button")
    public void testScrollUpAndScrollDown() {
        HomePage homePage = new HomePage(getWebDriver());

        homePage.scrollToFooter();
        Assert.assertTrue(homePage.isSubscriptionTitleDisplayed(), "Subscription title is not displayed in footer!");

        homePage.clickScrollUpArrow();
        Assert.assertTrue(homePage.isMainSliderTextDisplayed(), "Main slider text is not displayed after scrolling up!");
    }
}