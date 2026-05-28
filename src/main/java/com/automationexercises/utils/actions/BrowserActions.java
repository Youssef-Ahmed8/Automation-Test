package com.automationexercises.utils.actions;

import com.automationexercises.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;


public class BrowserActions {
private final WebDriver driver;
public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }
    //Maximize window
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }
    //get current URL
    public String getCurrentURL() {
        String url =driver.getCurrentUrl();
        LogsManager.info("Current URL: ", url);
        return url;
        }
        //Navigate to URL
        public void navigateTo(String url) {
            driver.navigate().to(url);
            LogsManager.info("Navigating to URL: ", url);
        }
        // Refresh the current page
        public void refreshPage() {
            driver.navigate().refresh();
            LogsManager.info("Refreshing the current page");
        }
        //close current window
        public void closeCurrentWindow() {
            driver.close();
            LogsManager.info("Closing the current window");
        }
        //open new window
        public void openNewWindow() {
            driver.switchTo().newWindow(org.openqa.selenium.WindowType.WINDOW);
        }
}
