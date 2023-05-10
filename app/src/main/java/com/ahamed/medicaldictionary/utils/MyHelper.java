package com.ahamed.medicaldictionary.utils;

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


}
