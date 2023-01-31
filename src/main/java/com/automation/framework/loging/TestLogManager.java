package com.automation.framework.loging;

import org.testng.ITestContext;
import org.testng.ITestResult;

public class TestLogManager {

    private ITestResult result;
    private ITestContext context;
    private String testName;
    private String methodName;
    private String browser;
    private static final String NO_ERROR = "No Errors!";
    private FileLogger[] loggers;

    public TestLogManager(ITestResult result, FileLogger[] loggers) {
        this.result = result;
        context = result.getTestContext();
        this.loggers = loggers;
    }

    public synchronized void log(boolean passed) {
        testName = context.getAttribute("TestName").toString();
        methodName = context.getAttribute("MethodName").toString();
        browser = context.getAttribute("Browser").toString();
        for (FileLogger logger : loggers) {
            logger.log(generateResult(passed));
        }
    }

    private TestResult generateResult(boolean passed) {
        TestResult testResult = new TestResult();
        testResult.setFullName(testName);
        testResult.setMethodName(methodName);
        testResult.setBrowser(browser);
        testResult.setPassed(passed);
        testResult.setError(passed ? NO_ERROR : result.getThrowable().getMessage());
        return testResult;
    }
}
