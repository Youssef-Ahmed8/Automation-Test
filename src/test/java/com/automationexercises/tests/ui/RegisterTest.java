package com.automationexercises.tests.ui;

import com.automationexercises.apis.UserManagementAPI;
import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.drivers.UITest;
import com.automationexercises.pages.SignUpLoginPage;
import com.automationexercises.pages.SignUpPage;
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


public class RegisterTest extends BaseTest {
    //tests
    String timestamp=TimeManger.getTimeStamp();
    @Epic("Automation Exercise")
    @Feature("UI User Management")
    @Story("User Register")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Youssef")
    @Test
    public void validSignUp() {
       new SignUpLoginPage(driver).navigate().
               enterSignUpName(testData.getJsonData("name")).
               enterSignUpEmail(testData.getJsonData("email")+timestamp+"@gmail.com").
               clickOnSignUpButton();
               new SignUpPage(driver).
               fillRegistrationForm(
                       testData.getJsonData("titleMale"),
                       testData.getJsonData("password"),
                       testData.getJsonData("day"),
                       testData.getJsonData("month"),
                       testData.getJsonData("year"),
                       testData.getJsonData("firstName"),
                       testData.getJsonData("lastName"),
                       testData.getJsonData("company"),
                       testData.getJsonData("address1"),
                       testData.getJsonData("address2"),
                       testData.getJsonData("country"),
                       testData.getJsonData("state"),
                       testData.getJsonData("city"),
                       testData.getJsonData("zipcode"),
                       testData.getJsonData("mobilePhone")
               )
               .clickCreateAccountButton().verifyAccountCreated();
    }
    @Test
    public void verifyErrorMessageWhenAccountCreatedBefore()
    {
        //precondition > create a user account
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password"),
                testData.getJsonData("titleMale"),
                testData.getJsonData("day"),
                testData.getJsonData("month"),
                testData.getJsonData("year"),
                testData.getJsonData("firstName"),
                testData.getJsonData("lastName"),
                testData.getJsonData("company"),
                testData.getJsonData("address1"),
                testData.getJsonData("address2"),
                testData.getJsonData("country"),
                testData.getJsonData("zipcode"),
                testData.getJsonData("state"),
                testData.getJsonData("city"),
                testData.getJsonData("mobilePhone")
        ).verifyUserCreatedSuccessfully();
        new SignUpLoginPage(driver).navigate().
                enterSignUpName(testData.getJsonData("name")).
                enterSignUpEmail(testData.getJsonData("email")+timestamp+"@gmail.com").
                clickOnSignUpButton().verifyRegisterErrorMsg(testData.getJsonData("messages.error"));
        new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+timestamp+"@gmail.com",
                testData.getJsonData("password")).verifyUserDeletedSuccessfully();
    }







    //configurations
    @BeforeClass
    public void preCondition(){
        testData=new JsonReader("register-data");
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
