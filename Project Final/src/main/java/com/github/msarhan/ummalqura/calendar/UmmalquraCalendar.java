

package com.github.msarhan.ummalqura.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;


public class UmmalquraCalendar extends GregorianCalendar {

    
    public final static int MUHARRAM = 0;

    
    public final static int SAFAR = 1;

    public final static int RABI_AWWAL = 2;

   
    public final static int RABI_THANI = 3;

    public final static int JUMADA_AWWAL = 4;

   
    public final static int JUMADA_THANI = 5;

   
    public final static int RAJAB = 6;

 
    public final static int SHAABAN = 7;

    public final static int RAMADHAN = 8;

    
    public final static int SHAWWAL = 9;

    
    public final static int THUL_QIDAH = 10;

    
    public final static int THUL_HIJJAH = 11;

    
    protected int hFields[];

   
    public UmmalquraCalendar() {
        this(TimeZone.getDefault(), Locale.getDefault());
    }

   
    public UmmalquraCalendar(Locale aLocale) {
        this(TimeZone.getDefault(), aLocale);
    }

    
    public UmmalquraCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
    }

   
    public UmmalquraCalendar(int year, int month, int dayOfMonth) {
        this(year, month, dayOfMonth, 0, 0, 0);
    }

   
    public UmmalquraCalendar(int year, int month, int dayOfMonth, int hourOfDay,
            int minute) {
        this(year, month, dayOfMonth, hourOfDay, minute, 0);
    }

   
    public UmmalquraCalendar(int year, int month, int dayOfMonth, int hourOfDay,
            int minute, int second) {

        set(YEAR, year);
        set(MONTH, month);
        set(DAY_OF_MONTH, dayOfMonth);
        set(HOUR_OF_DAY, hourOfDay);
        set(MINUTE, minute);
        set(SECOND, second);
    }

    
    public static int lengthOfMonth(int year, int month) {
        return UmmalquraGregorianConverter.getDaysInMonth(year, month);
    }

    public static int lengthOfYear(int year) {
        int total = 0;
        for (int m = MUHARRAM; m <= THUL_HIJJAH; m++) {
            total += lengthOfMonth(year, m);
        }

        return total;
    }

    @Override
    public int get(int field) {
        if (field == YEAR || field == MONTH || field == DAY_OF_MONTH) {
            return hFields[field];
        }

        return super.get(field);
    }

    @Override
    public void set(int field, int value) {
        if (field == YEAR || field == MONTH || field == DAY_OF_MONTH) {
            int[] hDateInfo = UmmalquraGregorianConverter.toHijri(getTime());
            if (field == YEAR) {
                hDateInfo[0] = value;
            } else if (field == MONTH) {
                hDateInfo[1] = value;
            } else {
                hDateInfo[2] = value;
            }

            int[] gDateInfo = UmmalquraGregorianConverter.toGregorian(hDateInfo[0], hDateInfo[1],
                    hDateInfo[2]);

            super.set(YEAR, gDateInfo[0]);
            super.set(MONTH, gDateInfo[1]);
            super.set(DAY_OF_MONTH, gDateInfo[2]);
            complete();

        } else {
            super.set(field, value);
        }

    }

   
    public int lengthOfMonth() {
        return lengthOfMonth(get(YEAR), get(MONTH));
    }

   
    public int lengthOfYear() {
        return lengthOfYear(get(YEAR));
    }


    @Override
    public String getDisplayName(int field, int style, Locale locale) {

        if (field == MONTH) {
            UmmalquraDateFormatSymbols symbols = new UmmalquraDateFormatSymbols(locale);
            String[] strings = getFieldStrings(field, style, symbols);
            if (strings != null) {
                int fieldValue = get(field);
                if (fieldValue < strings.length) {
                    return strings[fieldValue];
                }
            }

            return null;
        }

        return super.getDisplayName(field, style, locale);
    }

   
    public Map<String, Integer> getDisplayNames(int field, int style, Locale locale) {

        if (field == MONTH) {
            // ALL_STYLES
            if (style == ALL_STYLES) {
                Map<String, Integer> shortNames = getDisplayNamesImpl(field, SHORT, locale);
                Map<String, Integer> longNames = getDisplayNamesImpl(field, LONG, locale);
                if (shortNames == null) {
                    return longNames;
                }
                if (longNames != null) {
                    shortNames.putAll(longNames);
                }
                return shortNames;
            }

            // SHORT or LONG
            return getDisplayNamesImpl(field, style, locale);
        }

        return super.getDisplayNames(field, style, locale);
    }

    private Map<String, Integer> getDisplayNamesImpl(int field, int style, Locale locale) {
        UmmalquraDateFormatSymbols symbols = new UmmalquraDateFormatSymbols(locale);
        String[] strings = getFieldStrings(field, style, symbols);
        if (strings != null) {
            Map<String, Integer> names = new HashMap<String, Integer>();
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].length() == 0) {
                    continue;
                }
                names.put(strings[i], i);
            }
            return names;
        }

        return null;
    }

    private String[] getFieldStrings(int field, int style, UmmalquraDateFormatSymbols symbols) {
        if (field == MONTH) {
            if (SHORT == style) {
                return symbols.getShortMonths();
            }

            if (LONG == style) {
                return symbols.getMonths();
            }
        }

        return null;
    }

    public boolean equals(Object obj) {
        return obj instanceof UmmalquraCalendar && super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode() ^ 622;
    }

    @Override
    protected void computeFields() {
        super.computeFields();

        if (hFields == null) {
            hFields = new int[super.fields.length];
        }

        int[] hDateInfo = UmmalquraGregorianConverter.toHijri(time);
        hFields[Calendar.YEAR] = hDateInfo[0];
        hFields[Calendar.MONTH] = hDateInfo[1];
        hFields[Calendar.DAY_OF_MONTH] = hDateInfo[2];
    }

}
