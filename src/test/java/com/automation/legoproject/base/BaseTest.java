package com.automation.legoproject.base;

import com.automation.framework.driver.DriverFactory;
import com.automation.framework.driver.DriverManager;
import com.automation.framework.loging.Log4jLogger;
import com.automation.framework.utils.SeleniumCore;
import com.automation.framework.utils.JsonReader;
import lombok.Getter;
import lombok.Setter;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class BaseTest {
    protected SeleniumCore selenium;

    @Getter
    protected DriverManager driverManager;
    @Getter
    @Setter
    private JsonReader jsonReader;
    private static final String ENVIRONMENTS = "src/test/resources/env.json";
    private static final String REPORTS = "./Reports";

    private TestContext testContext;
    private static boolean suiteRan;

    @BeforeTest
    @Parameters("testDataPath")
    public void setup(@Optional String testDataPath) {
        driverManager = DriverFactory.getDriverManager();
        testContext = new TestContext(Reporter.getCurrentTestResult().getTestContext());
        String browser = System.getProperty("browser", "Edge");
        if (testDataPath != null) {
            setJsonReader(new JsonReader(testDataPath));
        }
        testContext.setTestContextDetails(
                Reporter.getCurrentTestResult().getTestContext().getAllTestMethods()[0].getMethodName(),
                jsonReader.getValue("Test_Name") + " : " + browser,
                browser);
        selenium = new SeleniumCore(driverManager.getDriver());
    }

    @BeforeMethod
    public void startDriver() {
        Log4jLogger.log("Test Started: " + testContext.getAttributeValue("JsonTestName"));
        selenium.get(new JsonReader(ENVIRONMENTS).getValue("Lego.URL"), "Launching website");
    }

    @AfterTest
    public void quitDriver() {
        driverManager.quit();
    }

    @BeforeSuite
    public void deleteReport() {
        if (!suiteRan) {
            suiteRan = true;
            Path reportFolder = Paths.get(REPORTS);
            if (Files.exists(reportFolder)) {
                try {
                    Files.walk(reportFolder)
                            .sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);
                } catch (IOException e) {
                    System.err.println("Error deleting Reports folder: " + e.getMessage());
                }
            }
        }
    }
}
