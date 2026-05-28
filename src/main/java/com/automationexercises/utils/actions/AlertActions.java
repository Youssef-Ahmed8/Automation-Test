package com.automationexercises.utils.actions;

import com.automationexercises.utils.WaitManager;
import com.automationexercises.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private WebDriver driver;
    private final WaitManager waitManager;
    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    //accept alert
    public void acceptAlert() {
        waitManager.fluentWait().until((WebDriver d) -> {
            try {
                d.switchTo().alert().accept();
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to accept alert: ", e.getMessage());
                return false;
            }
        });
    }
    //dismiss alert
    public void dismissAlert() {
        waitManager.fluentWait().until((WebDriver d) -> {
            try {
                d.switchTo().alert().dismiss();
                return true;
            } catch (Exception e) {
                LogsManager.error("Failed to dismiss alert: ", e.getMessage());
                return false;
            }
        });
    }
    //get alert text
    public String getAlertText() {
        return waitManager.fluentWait().until((WebDriver d) -> {
            try {
               String text= d.switchTo().alert().getText();
               return !text.isEmpty()? text : null;
            } catch (Exception e) {
                LogsManager.error("Failed to get alert text: ", e.getMessage());
                return null;
            }
        });
    }
    //send alert text
    public void sendAlertText(String text) {
        waitManager.fluentWait().until((WebDriver d) -> {
            try {
                d.switchTo().alert().sendKeys(text);
                return true;
            } catch (Exception e) {
               LogsManager.error("Failed to send text to alert: ", e.getMessage());
                return false;
            }
        });
    }
}
