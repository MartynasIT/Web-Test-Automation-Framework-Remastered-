package com.automation.legoproject.base;

import com.automation.framework.utils.SeleniumCore;

public class BasePage {
    protected SeleniumCore selenium;

    public BasePage(SeleniumCore seleniumCore) {
        this.selenium = seleniumCore;
    }
}
