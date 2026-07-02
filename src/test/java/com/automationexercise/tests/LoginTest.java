package com.automationexercise.tests;
import com.automationexercise.drivers.GUIDriver;
import com.automationexercise.drivers.UITest;
import com.automationexercise.pages.SignupLoginPage;
import com.automationexercise.pages.SignupPage;
import com.automationexercise.pages.components.NavigationBarComponent;
import com.automationexercise.utils.TimeManager;
import com.automationexercise.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("UI User Management")
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
@Owner("Ashraf")
@UITest
public class LoginTest extends BaseTest {

    String timestamp;

    @Description("Verify user can login with valid credentials")
    @Test
    public void validLoginTC() {
        String email = registerData.getJsonData("email") + timestamp + "@gmail.com";

        new SignupLoginPage(driver).navigate()
                .enterSignupName(registerData.getJsonData("name"))
                .enterSignupEmail(email)
                .clickSignupButton();

        new SignupPage(driver)
                .fillRegisterationForm(
                        registerData.getJsonData("titleMale"),
                        registerData.getJsonData("password"),
                        registerData.getJsonData("day"),
                        registerData.getJsonData("month"),
                        registerData.getJsonData("year"),
                        registerData.getJsonData("firstName"),
                        registerData.getJsonData("lastName"),
                        registerData.getJsonData("companyName"),
                        registerData.getJsonData("address1"),
                        registerData.getJsonData("address2"),
                        registerData.getJsonData("country"),
                        registerData.getJsonData("state"),
                        registerData.getJsonData("city"),
                        registerData.getJsonData("zipcode"),
                        registerData.getJsonData("mobileNumber")
                )
                .clickCreateAccountButton()
                .verifyAccountCreated()
                .clickContinueButton()
                .clickOnLogoutButton();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(email)
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLabel(registerData.getJsonData("name"));

        new NavigationBarComponent(driver)
                .clickOnDeleteAccountButton();
    }

    @Description("Verify user cannot login with invalid email")
    @Test
    public void inValidLoginUsingInvalidEmailTC() {
        new SignupLoginPage(driver).navigate()
                .enterLoginEmail("email@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .verifyLoginErrorMsg(testData.getJsonData("messages.error"));

    }

    @Description("Verify user cannot login with invalid password")
    @Test
    public void inValidLoginUsingInvalidPasswordTC() {
        String email = registerData.getJsonData("email") + timestamp + "@gmail.com";

        new SignupLoginPage(driver).navigate()
                .enterSignupName(registerData.getJsonData("name"))
                .enterSignupEmail(email)
                .clickSignupButton();

        new SignupPage(driver)
                .fillRegisterationForm(
                        registerData.getJsonData("titleMale"),
                        registerData.getJsonData("password"),
                        registerData.getJsonData("day"),
                        registerData.getJsonData("month"),
                        registerData.getJsonData("year"),
                        registerData.getJsonData("firstName"),
                        registerData.getJsonData("lastName"),
                        registerData.getJsonData("companyName"),
                        registerData.getJsonData("address1"),
                        registerData.getJsonData("address2"),
                        registerData.getJsonData("country"),
                        registerData.getJsonData("state"),
                        registerData.getJsonData("city"),
                        registerData.getJsonData("zipcode"),
                        registerData.getJsonData("mobileNumber")
                )
                .clickCreateAccountButton()
                .verifyAccountCreated()
                .clickContinueButton()
                .clickOnLogoutButton();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(email)
                .enterLoginPassword(registerData.getJsonData("password") + timestamp)
                .clickLoginButton()
                .verifyLoginErrorMsg(testData.getJsonData("messages.error"));

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(email)
                .enterLoginPassword(registerData.getJsonData("password"))
                .clickLoginButton();

        new NavigationBarComponent(driver)
                .clickOnDeleteAccountButton();
    }

    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("login-data");
        registerData = new JsonReader("register-data");
    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        timestamp = TimeManager.getSimpleTimestamp();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }

    @AfterMethod
    public void tearDown() {
        driver.quitDriver();
    }
}