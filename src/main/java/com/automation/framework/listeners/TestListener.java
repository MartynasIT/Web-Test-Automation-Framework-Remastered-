package com.automation.framework.listeners;

import com.automation.framework.loging.FileLogger;
import com.automation.framework.loging.HtmlLogger;
import com.automation.framework.loging.JsonLogger;
import com.automation.framework.loging.TestLogManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {


    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        new TestLogManager(result, new FileLogger[]{new HtmlLogger(), new JsonLogger()}).log(true);
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        new TestLogManager(result, new FileLogger[]{new HtmlLogger(), new JsonLogger()}).log(false);
    }

}
