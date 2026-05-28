package com.automationexercises.drivers;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriver {
    protected String downloadsPath = System.getProperty("user.dir") + "\\src\\test\\resources\\downloads";
    public abstract WebDriver createDriver();
}
