package com.automationexercises.validations;

import com.automationexercises.utils.logs.LogsManager;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class Validation extends BaseAssertion{
  private  static SoftAssert softAssert = new SoftAssert();
  public static boolean used=false;
    public Validation(WebDriver driver) {
        super(driver);
    }
    public Validation(){
        super();
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        used=true;
     softAssert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used=true;
      softAssert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        used=true;
      softAssert.assertEquals(actual, expected, message);
    }
    public static void assertAll(ITestResult result)
    { if (!used) return;
        try {
        softAssert.assertAll();
    }
    catch (AssertionError e) {
        LogsManager.error("Soft assertion failed: ", e.getMessage());
        result.setStatus(ITestResult.FAILURE);
        result.setThrowable(e);
    }
    finally {
        softAssert = new SoftAssert();
    }
    }
}



