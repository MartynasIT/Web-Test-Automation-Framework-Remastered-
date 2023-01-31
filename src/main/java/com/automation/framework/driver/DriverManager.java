package com.automation.framework.driver;

import com.automation.framework.loging.Log4jLogger;
import org.openqa.selenium.WebDriver;

public abstract class DriverManager implements Driver {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected abstract void createDriver();

    @Override
    public synchronized WebDriver getDriver() {
        if (driver.get() == null) {
            createDriver();
            Log4jLogger.log("WebDriver created");
        }
        return driver.get();
    }

    @Override
    public synchronized void close() {
        if (driver.get() != null) {
            driver.get().close();
            Log4jLogger.log("WebDriver close");
        }
    }

    @Override
    public synchronized void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.set(null);
            Log4jLogger.log("WebDriver session quit");
        }
    }
}

