package com.automationexercises.drivers;

public enum Browser {

    CHROME(new ChromeFactory()),
    FIREFOX(new FirefoxFactory()),
    EDGE(new EdgeFactory()),
    SAFARI(new SafariFactory());

    private final AbstractDriver driverFactory;

    Browser(AbstractDriver driverFactory) {
        this.driverFactory = driverFactory;
    }

    public AbstractDriver getDriverFactory() {
        return driverFactory;
    }
}