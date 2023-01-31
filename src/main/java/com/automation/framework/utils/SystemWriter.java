package com.automation.framework.utils;

import java.io.*;

public class SystemWriter {
    public static void makeDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void writeFile(String path, String content) {
        FileWriter file;
        try {
            file = new FileWriter(path);
            file.write(content);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}