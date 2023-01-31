package com.automation.legoproject.base;

import lombok.Getter;
import lombok.Setter;
import org.testng.ITestContext;

public class TestContext {
    @Setter
    @Getter
    private ITestContext context;

    public TestContext(ITestContext context) {
        setContext(context);
    }

    /**
     * To set specific test data
     *
     * @param methodName - Method name for faster debuging
     * @param testName   - Full test name from json
     * @param browser    - Browser
     */
    public synchronized void setTestContextDetails(String methodName, String testName, String browser) {
        getContext().setAttribute("TestName", testName);
        getContext().setAttribute("MethodName", methodName);
        getContext().setAttribute("Browser", browser);
    }

    /**
     * To set specific test data
     *
     * @param attribute -  String attribute value
     * @param value     - String value
     */
    public synchronized void setCustomAttribute(String attribute, String value) {
        getContext().setAttribute(attribute, value);
    }

    /**
     * To get specific detail from context
     *
     * @param attribute -  String attribute value
     */
    public synchronized Object getAttributeValue(String attribute) {
        return getContext().getAttribute(attribute);
    }
}
