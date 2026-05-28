package com.automationexercises.pages;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpLoginPage {
    private GUIDriver driver;
    public NavigationBarComponent navigationBarComponent;
    private final String signupLoginEndPoint= "/login";
    public SignUpLoginPage(GUIDriver driver) {
        this.driver = driver;
       this.navigationBarComponent=new NavigationBarComponent(driver);
    }


    //locators
    private final By loginEmail = By.cssSelector("[data-qa=\"login-email\"]");
    private final By loginPassword= By.cssSelector("[data-qa=\"login-password\"]");
    private final By loginButton= By.cssSelector("[data-qa=\"login-button\"]");
    private final By signUpName= By.cssSelector("[data-qa=\"signup-name\"]");
    private final By signUpEmail= By.cssSelector("[data-qa=\"signup-email\"]");
    private final By signUpButton= By.cssSelector("[data-qa=\"signup-button\"]");
    private final By signupLabel=By.cssSelector(".signup-form > h2");
    private final By loginError=By.xpath("//form[@action='/login']//p");
    private final By registerError=By.cssSelector(".signup-form  p");
    private final By logoutButton=By.xpath("//a[@href='/logout']");



    //actions
    @Step("Navigate to Register/Login Page")
    public SignUpLoginPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+signupLoginEndPoint);
        return this;
    }
    @Step("click on logout if you signup")
    public SignUpLoginPage logout(){
        driver.element().click(logoutButton);
        return this;
    }
    @Step("Enter name {email} in Login field")
    public SignUpLoginPage enterLoginEmail(String  email){
        driver.element().type(loginEmail,email);
        return this;
    }
    @Step("Enter password {password} in Login field")
    public SignUpLoginPage enterLoginPassword(String password){
        driver.element().type(loginPassword,password);
        return this;
    }
    @Step("Click on Login Button")
    public SignUpLoginPage clickOnLoginButton(){
        driver.element().click(loginButton);
        return this;
    }
    @Step("Enter name {name} in SignUp field")
    public SignUpLoginPage enterSignUpName(String name){
        driver.element().type(signUpName,name);
        return this;
    }
    @Step("Enter email {email} in SignUp field")
    public SignUpLoginPage enterSignUpEmail(String email){
        driver.element().type(signUpEmail,email);
        return this;
    }
    @Step("Click on SignUp Button")
    public SignUpLoginPage clickOnSignUpButton(){
        driver.element().click(signUpButton);
        return new SignUpLoginPage(driver);
    }
    //validations
    @Step("verify new user signup visible")
    public SignUpLoginPage verifyNewUserSignupVisible(){
        driver.verification().isElementVisible(signupLabel);
        return this;
    }
    @Step("verify login error message {errorExpected}")
    public SignUpLoginPage verifyLoginErrorMsg (String errorExpected){
        String errorActual= driver.element().getText(loginError);
        driver.verification().Equals(errorActual,errorExpected,"Expected error message: " + errorExpected + ", but got: " + errorActual);
        return this;
    }
    @Step("verify register error message {errorExpected}")
    public SignUpLoginPage verifyRegisterErrorMsg (String errorExpected) {
        String errorActual = driver.element().getText(registerError);
        driver.verification().Equals(errorActual, errorExpected, "Expected error message: " + errorExpected + ", but got: " + errorActual);
        return this;
    }


}
