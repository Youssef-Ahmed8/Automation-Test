package com.automationexercises.utils.actions;

import com.automationexercises.utils.WaitManager;
import com.automationexercises.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameActions {
    private final WebDriver driver;
    private final WaitManager waitManager;
    public FrameActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }
    //switch to frame by index
    public void switchToFrameByIndex(int index) {
        waitManager.fluentWait().until((WebDriver d) -> {
            try {
                d.switchTo().frame(index);
                LogsManager.info("Switched to frame with index: ", String.valueOf(index));
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    //switch to frame by name or id
    public void switchToFrameByNameOrId(String nameOrId) {
        waitManager.fluentWait().until((WebDriver d) -> {
            try {
                d.switchTo().frame(nameOrId);
                LogsManager.info("Switched to frame with name or id: ", nameOrId);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
    //switch to frame by WebElement
    public void switchToFrameByWebElement(By frameLocator) {
        waitManager.fluentWait().until((WebDriver d) -> {
            try {
                d.switchTo().frame(d.findElement(frameLocator));
                LogsManager.info("Switched to frame with locator: ", frameLocator.toString());
                return true;
            } catch (Exception e) {
                return false;    }
        });
    }
    //switch back to default content
    public void switchToDefaultContent() {
        waitManager.fluentWait().until((WebDriver d) ->{
            try {
                d.switchTo().defaultContent();
                LogsManager.info("Switched back to default content");
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
}
