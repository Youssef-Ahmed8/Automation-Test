package com.automationexercises.tests;

import com.automationexercises.drivers.GUIDriver;
import com.automationexercises.drivers.WebDriverProvider;
import com.automationexercises.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {
   protected GUIDriver driver;
   protected JsonReader testData;


    

    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
