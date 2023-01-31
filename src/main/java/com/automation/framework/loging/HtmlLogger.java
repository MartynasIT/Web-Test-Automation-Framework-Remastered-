package com.automation.framework.loging;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class HtmlLogger implements FileLogger {
    public final static String PATH = "./Reports/" + "Report.html";
    private static ExtentReports exReport;
    private static boolean isReportInitialized = false;

    public HtmlLogger() {
        CreateResultDir();
        initReport();
    }

    private void initReport() {
        if (!isReportInitialized) {
            exReport = new ExtentReports(PATH, false);
            isReportInitialized = true;
        }
    }

    public synchronized void log(TestResult result) {
        ExtentTest exTest = exReport.startTest(result.getFullName());
        if (result.isPassed()) {
            exTest.log(LogStatus.PASS, result.getError());
        } else {
            exTest.log(LogStatus.FAIL, result.getError());
        }
        exTest.log(LogStatus.INFO, "Method Name: " + result.getMethodName());
        exTest.log(LogStatus.INFO, "Browser: " + result.getBrowser());
        exReport.endTest(exTest);
        exReport.flush();
    }
}
