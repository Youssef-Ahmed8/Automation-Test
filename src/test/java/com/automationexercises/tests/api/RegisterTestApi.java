package com.automationexercises.tests.api;

import com.automationexercises.apis.UserManagementAPI;
import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.pages.components.NavigationBarComponent;
import com.automationexercises.tests.BaseTest;
import com.automationexercises.utils.TimeManger;
import com.automationexercises.utils.dataReader.JsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterTestApi extends BaseTest {
    String timestamp= TimeManger.getTimeStamp();
    @Test
    public void registerTest(){
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
    }
    //configurations
    @BeforeClass
    public void setup(){
        testData=new JsonReader("register-data");
    }

}
