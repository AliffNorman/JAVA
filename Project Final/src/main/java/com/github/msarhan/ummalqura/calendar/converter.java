package com.github.msarhan.ummalqura.calendar;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.ResourceBundle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.*;
import java.util.*;
import java.util.Scanner; 
public class converter{
    public static void main(String[] args) throws Exception {
              
      GregorianCalendar gCal = new  GregorianCalendar();
      Calendar uCal = new UmmalquraCalendar();
      
      String month[] = { "Jan", "Feb", "Mar", "Apr", 
                           "May", "Jun", "Jul", "Aug", 
                           "Sep", "Oct", "Nov", "Dec" }; 
       
       SimpleDateFormat dateFormat = new SimpleDateFormat("", Locale.ENGLISH);
       dateFormat.setCalendar(uCal);
       
       System.out.println("Gregorian to Hijri :\n");
      
      try {
      
       FileInputStream fstream = new FileInputStream("C:\\Users\\User\\Desktop\\Project Final\\inputGregorian.txt");
       //PrintStream out = new PrintStream( new File("C:\\Users\\alffnrmn\\Documents\\UiTM\\UiTM Diploma\\outputGregorian.txt"));
       PrintStream out2 = new PrintStream( new File("C:\\Users\\User\\Desktop\\Project Final\\Gregorian to Hijrah.txt"));
       DataInputStream in = new DataInputStream(fstream);
       BufferedReader br = new BufferedReader(new InputStreamReader(in));
   
       int day,month2,year;
       
         String indata=null;
           while((indata=br.readLine())!=null){
           
            StringTokenizer st=new StringTokenizer(indata,"/");
            day = Integer.parseInt(st.nextToken());
            month2 = Integer.parseInt(st.nextToken());
            year = Integer.parseInt(st.nextToken());
       
         month2=month2-1;
         gCal = new GregorianCalendar(year,month2,day);
         System.out.print("Date in Gregorian: " + month[gCal.get(Calendar.MONTH)] + " " + gCal.get(Calendar.DATE) + ", " + gCal.get(Calendar.YEAR)+"\t");
         //out.println("Date in Gregorian: " + month[gCal.get(Calendar.MONTH)] + " " + gCal.get(Calendar.DATE) + ", " + gCal.get(Calendar.YEAR)+"\t");
          
      uCal.setTime(gCal.getTime());

      uCal.get(Calendar.YEAR);         
      uCal.get(Calendar.MONTH);        
      uCal.get(Calendar.DAY_OF_MONTH); 
      
      dateFormat.applyPattern("MMMM");
      dateFormat.format(uCal.getTime());
      
      System.out.println("Date in Hijri: "+uCal.get(Calendar.DAY_OF_MONTH)+" "+dateFormat.format(uCal.getTime())+", "+ uCal.get(Calendar.YEAR));
      out2.println("Date in Gregorian: " + month[gCal.get(Calendar.MONTH)] + " " + gCal.get(Calendar.DATE) + ", " + gCal.get(Calendar.YEAR)+"\t,\t"+"Date in Hijri: "+uCal.get(Calendar.DAY_OF_MONTH)+" "+dateFormat.format(uCal.getTime())+", "+ uCal.get(Calendar.YEAR));
      
     }
       System.out.println("\nHijri to Gregorian :\n");
       
       
       Calendar uCal2;
       GregorianCalendar gCal2 = new GregorianCalendar(); 
       
      
      
       FileInputStream fstream2 = new FileInputStream("C:\\Users\\User\\Desktop\\Project Final\\inputHijrah.txt");
       //PrintStream out3 = new PrintStream( new File("C:\\Users\\alffnrmn\\Documents\\UiTM\\UiTM Diploma\\outputHijrah.txt"));
       PrintStream out4 = new PrintStream( new File("C:\\Users\\User\\Desktop\\Project Final\\Hijrah to Gregorian.txt"));
       DataInputStream in2 = new DataInputStream(fstream2);
       BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
  
       int day2,month3,year2;
       
         String indata2=null;
           while((indata2=br2.readLine())!=null){
           
            StringTokenizer st=new StringTokenizer(indata2,"/");
            day2 = Integer.parseInt(st.nextToken());
            month3 = Integer.parseInt(st.nextToken());
            year2 = Integer.parseInt(st.nextToken());
       
         uCal2 = new UmmalquraCalendar(year2,month3,day2);
         
         SimpleDateFormat dateFormat2 = new SimpleDateFormat("", Locale.ENGLISH);
         dateFormat2.setCalendar(uCal2);
         dateFormat2.applyPattern("MMMM");
         dateFormat2.format(uCal2.getTime());
         
         System.out.print("Date in Hijri: " + dateFormat2.format(uCal2.getTime()) + " " + uCal2.get(Calendar.DATE) + ", " + uCal2.get(Calendar.YEAR)+"\t");
         //out3.println("Date in Hijri: " + dateFormat2.format(uCal2.getTime())+ " " + uCal2.get(Calendar.DATE) + ", " + uCal2.get(Calendar.YEAR)+"\t");
       gCal2.setTime(uCal2.getTime());
      
      gCal2.get(Calendar.YEAR);         
      gCal2.get(Calendar.MONTH);        
      int day7=gCal2.get(Calendar.DAY_OF_MONTH); 
      
      
      System.out.println("Date in Gregorian: "+month[gCal2.get(Calendar.MONTH)]+" "+day7+", "+ gCal2.get(Calendar.YEAR));
      out4.println("Date in Hijri: " + dateFormat2.format(uCal2.getTime())+ " " + uCal2.get(Calendar.DATE) + ", " + uCal2.get(Calendar.YEAR)+"\t,\t"+"Date in Gregorian: "+month[gCal2.get(Calendar.MONTH)]+" "+day7+", "+ gCal2.get(Calendar.YEAR));
     
          }  
            
       in.close();
       //out.close();
       out2.close();
       in2.close();
       //out3.close();
       out4.close();
  
                                 
      }catch (Exception e){
        System.err.println("Error: " + e.getMessage()); 
    }
 
}
}

