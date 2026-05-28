package com.automationexercises.drivers;
import com.automationexercises.utils.dataReader.PropertyReader;
import com.automationexercises.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class FirefoxFactory extends AbstractDriver {
private final String remoteHost=PropertyReader.getProperty("remoteHost");
private final String remotePort=PropertyReader.getProperty("remotePort");
    private FirefoxOptions getOptions() {

        FirefoxOptions options = new FirefoxOptions();

        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        if(PropertyReader.getProperty("executionType").equalsIgnoreCase("localHeadless") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("remoteHeadless")) {
            options.addArguments("--headless=new");}
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return options;
    }

    @Override
    public WebDriver createDriver() {

        if (PropertyReader.getProperty("executionType")
                .equalsIgnoreCase("Local")
                ||
                PropertyReader.getProperty("executionType")
                        .equalsIgnoreCase("LocalHeadless")) {

            return new FirefoxDriver(getOptions());

        } else if (PropertyReader.getProperty("executionType")
                .equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI(
                                "http://"
                                        + remoteHost
                                        + ":"
                                        + remotePort
                                        + "/wd/hub").toURL(), getOptions());

            } catch (Exception e) {
                LogsManager.error("Failed to create RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create RemoteWebDriver", e);
            }


        } else {

            LogsManager.error(
                    "Invalid execution type: "
                            + PropertyReader.getProperty("executionType")
            );

            throw new IllegalArgumentException(
                    "Invalid execution type: "
                            + PropertyReader.getProperty("executionType")
            );
        }
    }
    }