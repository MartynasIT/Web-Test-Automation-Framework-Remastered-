package com.automation.framework.loging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonLogger implements FileLogger {
    public static final String PATH = "./Reports/" + "RunResults.json";
    private List<TestResult> testResults = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public JsonLogger() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        CreateResultDir();
        try {
            TestResult[] existingTestResults = mapper.readValue(new File(PATH), TestResult[].class);
            for (TestResult testResult : existingTestResults) {
                testResults.add(testResult);
            }
        } catch (IOException ignored) {
        }
    }

    public synchronized void log(TestResult result) {
        testResults.add(result);
        try {
            mapper.writeValue(new File(PATH), testResults);
        } catch (IOException e) {
            System.out.println("Error writing results to file: " + e.getMessage());
        }
    }
}
