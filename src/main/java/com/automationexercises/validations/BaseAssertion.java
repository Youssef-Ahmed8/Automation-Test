package com.automationexercises.validations;
import com.automationexercises.FileUtils;
import com.automationexercises.utils.WaitManager;
import com.automationexercises.utils.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseAssertion {

    protected  WebDriver driver;
    protected  WaitManager waitManager;
    protected  ElementActions elementActions;
    protected BaseAssertion(){

    }

    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }

    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String actual,String expected,String message);
    public void Equals(String actual, String expected, String message) {
        assertEquals(actual, expected, message);
    }

    public void isElementVisible(By locator) {

      boolean flag=waitManager.fluentWait().until((WebDriver driver1) -> {

            try {
                driver1.findElement(locator).isDisplayed();

                return true;

            } catch (Exception e) {
                return false;
            }
        });
        assertTrue(flag, "Element should be visible: " + locator);

    }
    //verify page url
    public void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "Expected URL: " + expectedUrl + ", but got: " + actualUrl);
    }
    //verify page title
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Expected title: " + expectedTitle + ", but got: " + actualTitle);
    }
    //verify that file exists
    public void assertFileExists(String fileName,String message) {
        waitManager.fluentWait().until(d-> FileUtils.isFileExists(fileName));
        assertTrue(FileUtils.isFileExists(fileName), message);
    }
}