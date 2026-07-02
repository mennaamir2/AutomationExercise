package com.automationexercise.tests;
import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.drivers.UITest;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.ProductsPage;
import com.automationexercise.pages.SignupLoginPage;
import com.automationexercise.pages.SignupPage;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.utils.TimeManager;
import com.automationexercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.*;
@Epic("Checkout Management")
@Feature("UI Checkout Management")
@Story("Checkout Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Ashraf")
@UITest
public class CheckoutTest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();

    @Test
    public void registerNewAccount() {
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email")+ timestamp+"@gmail.com")
                .clickSignupButton();

        new SignupPage(driver)
                .fillRegisterationForm(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("password"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")
                )
                .clickCreateAccountButton()
                .verifyAccountCreated()
                .clickContinueButton()
                .clickOnLogoutButton();
    }
    @Test(dependsOnMethods = "registerNewAccount")
    public void loginToAccount() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLabel(testData.getJsonData("name"));
    }
    @Test(dependsOnMethods = {"loginToAccount","registerNewAccount"})
    public void addProductToCart() {
        new ProductsPage(driver).navigate()
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsOnCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total")
                );
    }
    @Test(dependsOnMethods ={"addProductToCart","loginToAccount","registerNewAccount"} )
    public void checkout() {
        new CartPage(driver)
                .clickOnProceedToCheckout()
                .verifyDeliveryAddress(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber")
                )
                .verifyBillingAddress(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("companyName"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("city"),
                        testData.getJsonData("state"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("country"),
                        testData.getJsonData("mobileNumber")
                );
    }

    @Test(dependsOnMethods = {"checkout","loginToAccount","registerNewAccount"})
    public void deleteAccountAsPostCondition() {
        new NavigationBarComponent(driver)
                .clickOnDeleteAccountButton();
    }

    @BeforeClass
    protected void setUp() {
        testData = new JsonReader("checkout-data");

        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }


    @AfterClass
    public void tearDown() {
        driver.quitDriver();
    }
}
