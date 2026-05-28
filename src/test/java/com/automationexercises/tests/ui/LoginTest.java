package com.automationexercises.tests.ui;

import com.automationexercises.apis.UserManagementAPI;
import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.drivers.UITest;
import com.automationexercises.pages.SignUpLoginPage;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.tests.BaseTest;
import com.automationexercises.utils.TimeManger;
import com.automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@UITest

public class LoginTest extends BaseTest {
String timestamp= TimeManger.getTimeStamp();
    @Epic("Automation Exercise")
    @Feature("UI User Management")
    @Story("User Login")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Youssef")
    //tests
    @Description("Verify that user can login with valid data")
    @Test
    public void validLoginTc(){
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName")).verifyUserCreatedSuccessfully();
        new SignUpLoginPage(driver).navigate().enterLoginEmail(testData.getJsonData("email")+timestamp+"@gmail.com").enterLoginPassword(testData.getJsonData("password")).
                clickOnLoginButton().navigationBarComponent.verifyUserLabel(testData.getJsonData("name"));
        new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password")).verifyUserDeletedSuccessfully();
    }
    //tests
    @Description("Verify that user can't login with invalid data")
    @Test
    public void invalidLoginUsingInvalidEmailTc(){
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName")).verifyUserCreatedSuccessfully();
        new SignUpLoginPage(driver).navigate().enterLoginEmail(testData.getJsonData("email") + "@gmail.com").enterLoginPassword(testData.getJsonData("password")).
                clickOnLoginButton().verifyLoginErrorMsg(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password")).verifyUserDeletedSuccessfully();
    }
    @Test
    public void invalidLoginUsingInvalidPasswordTc(){
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName")).verifyUserCreatedSuccessfully();
        new SignUpLoginPage(driver).navigate().enterLoginEmail(testData.getJsonData("email")+timestamp+"@gmail.com").enterLoginPassword(testData.getJsonData("password")+timestamp).
                clickOnLoginButton().verifyLoginErrorMsg(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password")).verifyUserDeletedSuccessfully();
    }










    //configurations
    @BeforeClass
    public void preCondition(){
        testData=new JsonReader("login-data");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver=new GUIDriver();
        new NavigationBarComponent(driver).navigate();
    }
    @AfterMethod
    public void afterMethod() {
        driver.quitDriver();
    }


}


