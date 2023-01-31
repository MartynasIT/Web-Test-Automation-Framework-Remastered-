package com.automation.framework.utils;

import java.util.Arrays;


public class ArrayComparer {

    public static boolean are2DArraysSame(String[][] array1, String[][] array2) {
        return Arrays.deepEquals(array1, array2);
    }
}
