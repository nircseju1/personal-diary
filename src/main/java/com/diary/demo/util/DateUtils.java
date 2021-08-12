package com.diary.demo.util;

import java.time.LocalDateTime;

public class DateUtils {
    private static final String DATE_FORMAT_NOW = "ddMMyyyy_hhmmssa";
    private static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";

    public static LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now();
    }
}
