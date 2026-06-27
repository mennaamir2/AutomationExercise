package com.automationexercise.utils;
public class TimeManager {
    //screenshots - logs - reports
    public static String getTimestamp() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new java.util.Date());
    }
    //unique timestamp for each data
    public static String getSimpleTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

}
