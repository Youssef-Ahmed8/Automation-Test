package com.automationexercises.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeManger {
    public static String getTimeStamp(){
        return new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss")
                .format(new Date());
    }
    public  static String getTimeStampForFileName(){
        return Long.toString(System.currentTimeMillis());
    }
}
