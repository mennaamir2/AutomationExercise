package com.automationexercises.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    private By commentTextArea = By.cssSelector("textarea.form-control");
    private By placeOrderButton = By.cssSelector("a[href='/payment']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDescriptionComment(String comment) {
        driver.findElement(commentTextArea).sendKeys(comment);
    }

    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
    }
}