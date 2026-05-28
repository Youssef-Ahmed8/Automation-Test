package com.automationexercises.pages.components;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.pages.*;
import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private GUIDriver driver;

    public NavigationBarComponent(GUIDriver driver) {
        this.driver = driver;
    }

    // locators
    private final By homeButton = By.xpath("//a[.=' Home']");
    private final By productButton = By.cssSelector("a[href='/products']");
    private final By cartButton= By.xpath("//a[.=' Cart']");
    private final By signUpLoginButton= By.xpath("//a[.=' Signup / Login']");
    private final By logoutButton= By.xpath("//a[.=' Logout']");
    private final By deleteAccountButton= By.xpath("//a[.=' Delete Account']");;
    private final By testCasesButton= By.xpath("//a[.=' Test Cases']");
    private final By apiTestingButton= By.xpath("//a[.=' API Testing']");
    private final By contactUsButton= By.xpath("//a[.=' Contact us']");
    private final By VideoTutorialsButton= By.xpath("//a[.=' Video Tutorials']");;
    private final By homePageLabel= By.cssSelector("h1 > span");
    private final By userLabel= By.tagName("b");

    //actions
    @Step("Navigate to Home Page")
    public NavigationBarComponent navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }
    @Step("Click on Home Button")
    public NavigationBarComponent clickOnHomeButton() {
        driver.element().click(homeButton);
        return this;
    }
    @Step("Click on Product Button")
    public ProductsPage clickOnProductButton() {
        driver.element().click(productButton);
        return new ProductsPage(driver);
    }
    @Step("Click on Cart Button")
    public CartPage clickOnCartButton() {
        driver.element().click(cartButton);
        return new CartPage(driver);
    }
    @Step("Click on SignUp/Login Button")
    public SignUpLoginPage clickOnSignUpLoginButton() {
        driver.element().click(signUpLoginButton);
        return new SignUpLoginPage(driver);
    }
    @Step("Click on Logout Button")
        public LogoutPage clickOnLogoutButton() {
            driver.element().click(logoutButton);
            return new LogoutPage(driver);
    }
    @Step("Click on Delete Account Button")
    public DeleteAccountPage clickOnDeleteAccountButton() {
        driver.element().click(deleteAccountButton);
        return new DeleteAccountPage(driver);
    }
    @Step("Click on Test Cases Button")
    public TestCasesPage clickOnTestCasesButton() {
        driver.element().click(testCasesButton);
        return new TestCasesPage(driver);
    }
    @Step("Click on Contact Us Button")
    public ContactUsPage clickOnContactUsButton() {
        driver.element().click(contactUsButton);
        return new ContactUsPage(driver);
    }
    //validation
    @Step("Verify Home Page is visible successfully")
    public NavigationBarComponent verifyHomePage() {
        driver.verification().isElementVisible(homePageLabel);;
        return this;
    }
    @Step("Verify User Label")
    public NavigationBarComponent verifyUserLabel(String expectedName) {
        String actualName= driver.element().getText(userLabel);
        LogsManager.info("Verifying User Label: ", actualName);
        driver.verification().Equals(actualName, expectedName, "Expected user name: " + expectedName + ", but got: " + actualName);
        return this;
    }



}