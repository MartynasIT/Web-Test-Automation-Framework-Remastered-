package com.automation.framework.driver;

import org.openqa.selenium.WebDriver;

interface Driver {
    WebDriver getDriver();

    void close();

    void quit();
}