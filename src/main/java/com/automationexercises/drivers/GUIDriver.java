package com.automationexercises.drivers;
import com.automationexercises.utils.actions.AlertActions;
import com.automationexercises.utils.actions.BrowserActions;
import com.automationexercises.utils.actions.ElementActions;
import com.automationexercises.utils.actions.FrameActions;
import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;
import com.automationexercises.validations.Validation;
import com.automationexercises.validations.Verification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIDriver {
    private final String browser = PropertyReader.getProperty("browserType");
    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUIDriver() {
        LogsManager.info("Initializing WebDriver for browser: " + browser);
        Browser browserType = Browser.valueOf(browser.toUpperCase());
        LogsManager.info("Initializing WebDriver for browser: ", browserType.name());
        AbstractDriver abstractDriver = browserType.getDriverFactory();
        WebDriver driver = ThreadGuard.protect(abstractDriver.createDriver());
        driverThreadLocal.set(driver);
    }

    public ElementActions element() {
        return new ElementActions(get());
    }
    public BrowserActions browser() {
        return new BrowserActions(get());
    }
    public FrameActions frame() {
        return new FrameActions(get());
    }
    public AlertActions alert() {
        return new AlertActions(get());
    }
    //soft assert
    public Validation validation() {
        return new Validation(get());
    }
    //hard assert
    public Verification verification() {
        return new Verification(get());
    }


    public WebDriver get() {
        return driverThreadLocal.get();
    }

    public void quitDriver() {
        driverThreadLocal.get().quit();
    }
}