package com.automation.framework.driver;

import com.automation.framework.loging.Log4jLogger;

public class DriverFactory {
    public enum DriverType {
        CHROME,
        EDGE
    }

    private static final DriverType DEFAULT_DRIVER_TYPE = DriverType.CHROME;

    public static DriverManager getDriverManager() {
        DriverManager driverManager;
        DriverType driverType = getBrowserType();
        switch (driverType) {
            case EDGE:
                driverManager = new EdgeDriverManager();
                break;
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            default:
                throw new IllegalStateException("Unexpected browser type: " + driverType);
        }
        Log4jLogger.log("Selected browser type: " + driverType);
        return driverManager;
    }

    private static DriverType getBrowserType() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            Log4jLogger.log("Browser type is not specified, using default: " + DEFAULT_DRIVER_TYPE);
            return DEFAULT_DRIVER_TYPE;
        }
        try {
            return DriverType.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            Log4jLogger.log("Invalid browser type " + browser + " using default: " + DEFAULT_DRIVER_TYPE);
            return DEFAULT_DRIVER_TYPE;
        }
    }
}