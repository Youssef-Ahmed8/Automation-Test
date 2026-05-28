package com.automationexercises.pages;
import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.pages.components.NavigationBarComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpPage {
    private final GUIDriver driver;

    public SignUpPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By name = By.cssSelector("[data-qa=\"name\"]");
    private final By email = By.cssSelector("[data-qa=\"email\"]");
    private final By password = By.id("password");
    private final By day = By.id("days");
    private final By month = By.id("months");
    private final By year = By.id("years");
    private final By newsletter = By.id("newsletter");
    private final By offers = By.id("optin");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("[data-qa=\"create-account\"]");
    private final By accountCreatedLabel= By.cssSelector("[data-qa=\"account-created\"]");
    private final By continueButton= By.cssSelector("[data-qa=\"continue-button\"]");


    //actions
    @Step("choose title {title}")
    private SignUpPage chooseTitle(String Title) {
        By titleLocator = By.xpath("//input[@value='" + Title + "']");
        driver.element().click(titleLocator);
        return this;

    }

    @Step("Fill Registration Form")
    public SignUpPage fillRegistrationForm(String Title, String password, String day, String month, String year, String firstName, String lastName, String company, String address1, String address2, String country, String state, String city, String zipcode, String mobileNumber) {
        chooseTitle(Title);
        driver.element().type(this.password, password);
        driver.element().selectFromDropdown(this.day, day);
        driver.element().selectFromDropdown(this.month, month);
        driver.element().selectFromDropdown(this.year, year);
        driver.element().click(this.newsletter);
        driver.element().click(this.offers);
        driver.element().type(this.firstName, firstName);
        driver.element().type(this.lastName, lastName);
        driver.element().type(this.company, company);
        driver.element().type(this.address1, address1);
        driver.element().type(this.address2, address2);
        driver.element().selectFromDropdown(this.country, country);
        driver.element().type(this.state, state);
        driver.element().type(this.city, city);
        driver.element().type(this.zipcode, zipcode);
        driver.element().type(this.mobileNumber, mobileNumber);
        return this;
    }

    @Step("click Create Account Button")
    public SignUpPage clickCreateAccountButton() {
        driver.element().click(createAccountButton);
        return this;
    }
    @Step("click continue button")
    public NavigationBarComponent clickContinueButton() {
        driver.element().click(continueButton);
        return new NavigationBarComponent(driver);
    }


    //validation
    @Step("get Create Account Label")
    public SignUpPage verifyAccountCreated() {
        driver.verification().isElementVisible(accountCreatedLabel);
        return this;


    }
}