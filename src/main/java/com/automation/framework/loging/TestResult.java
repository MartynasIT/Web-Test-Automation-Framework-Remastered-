package com.automation.framework.loging;

public class TestResult {
    private String fullName;
    private String methodName;
    private String browser;
    private boolean passed;
    private String error;

    public String getMethodName() {
        return methodName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFullName() {
        return fullName;
    }
}