package com.ahamed.medicaldictionary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyHelper {
    public static String cleanText(String text) {
        String nDefinition = text.trim().replace("[", "");
        String mDefinition = nDefinition.replace("\"s\"", "");
        String oDefinition = mDefinition.trim().replace("\"", "");
        return oDefinition.trim().replace("],", "");
    }

    public static String getToUpperCase(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static String getDate() {
        return new SimpleDateFormat("EEE, d MMM yyyy, HH:mm", Locale.getDefault()).format(new Date());
    }



}
