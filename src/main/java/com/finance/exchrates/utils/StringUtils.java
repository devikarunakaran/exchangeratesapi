package com.finance.exchrates.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;


public class StringUtils {

    /**
     * Utility method to tokenize a given string with the supplied token
     *
     * @param input	The String to be tokenized
     * @param token	The token
     * @return 		The array of tokenized strings
     */
    public static String[] stringTokenize(String input, String token) {
        if (input == null || token == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(input, token);
        String arr[] = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreElements()) {
            arr[i] = st.nextToken();
            i++;
        }
        return arr;
    }

    public static Vector getArrayAsVector(Object[] objArray) {
        if (objArray == null || objArray.length == 0) {
            return null;
        }
        return new Vector(Arrays.asList(objArray));
    }

    /**
     * Gets the exception's stacktrace as a String
     * @param e
     * @return
     */
    public static String getStackTrace(Throwable e) {
        if (e == null) {
            return null;
        }
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    /**
     * gets the date as string from a GregorianCalendar object
     * @param gc
     * @return
     */
    public static String getDate(GregorianCalendar gc) {
        return ((gc.get(Calendar.MONTH) + 1) + "/" + gc.get(Calendar.DAY_OF_MONTH) + "/" + gc.get(Calendar.YEAR));
    }



    public static boolean equals(String val1, String val2) {
        return val1 == null ? val2 == null : val1.equals(val2);
    }

    public static boolean equalsIgnoreCase(String val1, String val2) {
        return val1 == null ? val2 == null : val1.equalsIgnoreCase(val2);
    }

    /**
     * Checks for absence
     * null== true, ""== true " "==true, "abc " =false
     * @param val
     * @return
     */
    public static boolean isNullOrEmpty(String val) {
        return val == null || val.trim().length() == 0;
    }


    /**
     * Checks for presence of value
     * null== false, ""== false, " "==false, "abc "=true
     * @param val
     * @return
     */
    public static boolean hasText(String val) {
        if (val != null && val.trim().length() > 0)
            return true;

        return false;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}

