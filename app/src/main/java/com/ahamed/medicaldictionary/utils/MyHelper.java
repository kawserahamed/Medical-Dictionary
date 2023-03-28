package com.ahamed.medicaldictionary.utils;

public class MyHelper {
    public static String cleanText(String text) {
        String nDefinition = text.trim().replace("[", "");
        String mDefinition = nDefinition.replace("\"s\"", "");
        String oDefinition = mDefinition.replace("\"", "");
        return oDefinition.replace("],", "");
    }
}
