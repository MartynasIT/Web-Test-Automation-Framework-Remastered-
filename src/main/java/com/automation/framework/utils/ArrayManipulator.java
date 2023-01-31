package com.automation.framework.utils;

import java.util.Arrays;
import java.util.Objects;

public class ArrayManipulator {

    public static String[] cleanArray(String[] array) {
        return Arrays.stream(array).filter(Objects::nonNull).toArray(String[]::new);
    }
}
