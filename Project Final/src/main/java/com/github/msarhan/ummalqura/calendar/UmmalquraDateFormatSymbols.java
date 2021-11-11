

package com.github.msarhan.ummalqura.calendar;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;


class UmmalquraDateFormatSymbols {

    
    Locale locale = null;

   
    String[] months = null;

    String[] shortMonths = null;

    public UmmalquraDateFormatSymbols() {
        initializeData(Locale.getDefault(Locale.Category.FORMAT));
    }

    public UmmalquraDateFormatSymbols(Locale locale) {
        initializeData(locale);
    }

    public String[] getMonths() {
        return Arrays.copyOf(months, months.length);
    }

   
    public String[] getShortMonths() {
        return Arrays.copyOf(shortMonths, shortMonths.length);
    }

    public void initializeData(Locale desiredLocale) {
        if (!("ar".equalsIgnoreCase(desiredLocale.getLanguage()) || "en"
                .equalsIgnoreCase(desiredLocale.getLanguage()))) {
            throw new IllegalArgumentException("Supported locales are 'English' and 'Arabic'");
        }
        locale = desiredLocale;

        // Initialize the fields from the ResourceBundle for locale.
        ResourceBundle resource = ResourceBundle
                .getBundle("com.github.msarhan.ummalqura.calendar.text.UmmalquraFormatData",
                        locale);

        months = resource.getStringArray("MonthNames");
        shortMonths = resource.getStringArray("MonthAbbreviations");
    }

}
