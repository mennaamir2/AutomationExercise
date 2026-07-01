package com.automationexercises.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private final WebDriver driver;

    private final By allProductsTitle = By.cssSelector(".features_items h2.text-center");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By productItems = By.cssSelector(".single-products");
    private final By addToCartButton = By.cssSelector(".overlay-content .add-to-cart");
    private final By viewCartLink = By.xpath("//u[text()='View Cart']");
    private final By secondProductAddToCartBtn = By.xpath("(//a[@data-product-id='2'])[1]");
    private final By continueShoppingBtn = By.cssSelector(".close-modal");
    private final By firstProductViewDetailsBtn = By.xpath("(//a[contains(@href, '/product_details/')])[1]");
    private final By quantityInput = By.id("quantity");
    private final By addToCartDetailsBtn = By.cssSelector("button.cart");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAllProductsTitleDisplayed() {
        return driver.findElement(allProductsTitle).isDisplayed();
    }

    public void searchForProduct(String productName) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public boolean areProductsDisplayedAfterSearch() {
        List<WebElement> products = driver.findElements(productItems);
        return !products.isEmpty();
    }

    public void addFirstProductToCart() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement targetButton = driver.findElement(addToCartButton);
        js.executeScript("arguments[0].click();", targetButton);
    }

    public void addSecondProductToCart() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement targetButton = driver.findElement(secondProductAddToCartBtn);
        js.executeScript("arguments[0].click();", targetButton);
    }

    public void clickContinueShopping() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn)).click();
    }

    public CartPage clickViewCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }

    public void clickFirstProductViewDetails() {
        driver.findElement(firstProductViewDetailsBtn).click();
    }

    public void setProductQuantity(String qty) {
        WebElement quantityField = driver.findElement(quantityInput);
        quantityField.clear();
        quantityField.sendKeys(qty);
    }

    public void clickAddToCartFromDetails() {
        driver.findElement(addToCartDetailsBtn).click();
    }
}