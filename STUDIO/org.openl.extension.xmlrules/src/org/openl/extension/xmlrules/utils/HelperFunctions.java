package org.openl.extension.xmlrules.utils;

import java.lang.reflect.Array;

import org.openl.extension.xmlrules.java.api.FilteredValue;

public class HelperFunctions {
    public static <T> T[][] transpose(T[][] arr) {
        if (arr.length == 0) {
            return arr;
        }

        Class clazz = arr[0].getClass().getComponentType();
        @SuppressWarnings("unchecked")
        T[][] newArr = (T[][]) Array.newInstance(clazz, arr[0].length, arr.length);
        for (int i = 0; i < arr.length; i++) {
            T[] row = arr[i];
            for (int j = 0; j < row.length; j++) {
                newArr[j][i] = row[j];
            }
        }
        return newArr;
    }

    public static Double toDouble(Object x) {
        if (x instanceof FilteredValue) {
            x = ((FilteredValue) x).getValue();
        }

        if (x == null) {
            return null;
        }

        if (x instanceof Double) {
            return (Double) x;
        }

        if (x instanceof String) {
            return Double.valueOf((String) x);
        }

        // Other number types
        if (x instanceof Number) {
            return ((Number) x).doubleValue();
        }

        throw new IllegalArgumentException("Can't convert to double");
    }
}