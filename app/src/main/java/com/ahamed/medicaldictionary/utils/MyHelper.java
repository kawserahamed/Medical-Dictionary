package com.ahamed.medicaldictionary.utils;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.TextView;

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
    public static void setBoldNormalText(String bold, String normal, TextView textView){
        SpannableStringBuilder builder=new SpannableStringBuilder();
        SpannableString txtSpannable= new SpannableString(bold);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        txtSpannable.setSpan(boldSpan, 0, bold.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(txtSpannable);
        builder.append(normal);
        textView.setText(builder, TextView.BufferType.SPANNABLE);
    }


}
