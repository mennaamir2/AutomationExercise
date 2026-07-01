package com.automationexercises.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {
    private final WebDriver driver;

    private final By cartRows = By.cssSelector("#cart_info_table tbody tr");
    private final By proceedToCheckoutButton = By.cssSelector(".check_out");
    private final By registerLoginLink = By.xpath("//div[@id='checkoutModal']//u[text()='Register / Login']");
    private final By deleteProductButton = By.cssSelector(".cart_quantity_delete");
    private final By emptyCartMessage = By.id("empty_cart");
    private final By productQuantityCount = By.cssSelector(".cart_quantity button");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartNotEmpty() {
        List<WebElement> items = driver.findElements(cartRows);
        return !items.isEmpty();
    }

    public void clickProceedToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
    }

    public HomePage clickRegisterLoginFromModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(registerLoginLink)).click();
        return new HomePage(driver);
    }

    public String getFirstProductTotalPrice() {
        return driver.findElement(By.xpath("//tr[@id='product-1']//p[@class='cart_total_price']")).getText();
    }

    public String getSecondProductTotalPrice() {
        return driver.findElement(By.xpath("//tr[@id='product-2']//p[@class='cart_total_price']")).getText();
    }

    public void removeFirstProduct() {
        driver.findElement(deleteProductButton).click();
    }

    public boolean isEmptyCartMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage)).isDisplayed();
    }

    public String getProductQuantity() {
        return driver.findElement(productQuantityCount).getText();
    }
}