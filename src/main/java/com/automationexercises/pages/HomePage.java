package com.automationexercises.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    private By productsMenuLink = By.cssSelector("a[href='/products']");
    private By cartMenuLink = By.cssSelector("a[href='/view_cart']");
    private By homePageLogo = By.cssSelector(".logo img");
    private By subscriptionTitle = By.cssSelector(".single-widget h2");
    private By scrollUpArrow = By.id("scrollUp");
    private By mainSliderText = By.cssSelector("#slider-carousel .active h2");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(homePageLogo).isDisplayed();
    }

    public ProductsPage clickOnProductsMenu() {
        driver.findElement(productsMenuLink).click();
        return new ProductsPage(driver);
    }

    public CartPage clickOnCartMenu() {
        driver.findElement(cartMenuLink).click();
        return new CartPage(driver);
    }

    public void scrollToFooter() {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public boolean isSubscriptionTitleDisplayed() {
        return driver.findElement(subscriptionTitle).isDisplayed();
    }

    public void clickScrollUpArrow() {
        driver.findElement(scrollUpArrow).click();
    }

    public boolean isMainSliderTextDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mainSliderText)).isDisplayed();
    }
}