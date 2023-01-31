package com.automation.framework.loging;

import com.automation.framework.utils.SystemWriter;

public interface FileLogger {
    void log(TestResult result);

    default void CreateResultDir() {
        SystemWriter.makeDir("./Reports/");
    }
}
