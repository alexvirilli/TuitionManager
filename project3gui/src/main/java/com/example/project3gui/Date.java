package com.example.project3gui;


import java.util.Calendar;
import java.util.Scanner;

/**
 * Date.java
 * Date class for storing and comparing dates. Contains methods for comparing, printing, parsing dates,
 * and checking for validity.
 * @author Alex Virilli, Ryan Elizondo-Fallas
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    Calendar c = Calendar.getInstance();

    /**
     * toString method for printing dates
     * @return String of the date in the format mm/dd/yyyy
     */
    @Override
    public String toString(){
        String  str = "";
        str = str + this.month + "/";
        str = str + this.day + "/";
        str = str + this.year;
        return str;
    }

    /**
     * equals method for comparing dates
     * @param Date
     * @return true if the dates are equal, false if not
     */
    @Override
    public boolean equals(Object Date){
        if(this == Date) return true;
        if(!(Date instanceof Date)) return false;
        Date D = (Date) Date;
        if(this.day == D.getDay() && this.month == D.getMonth() && this.year == D.getYear()) return true;
        return false;
    }

    /**
     * compareTo method for comparing dates
     * @param Date
     * @return -1 if the instance object is less than the parameter object, 1 if larger, 0 if equal
     */
    @Override
    public int compareTo(Date Date){
        if(this.year < Date.getYear()) return -1;
        if(this.year > Date.getYear()) return 1;
        if(this.month < Date.getMonth()) return -1;
        if(this.month > Date.getMonth()) return 1;
        if(this.day < Date.getDay()) return -1;
        if(this.day > Date.getDay()) return 1;
        return 0;
    }

    /**
     * Constructor for Date class
     */
    public Date() {
        this.year = c.get(Calendar.YEAR);
        this.day = c.get(Calendar.DAY_OF_MONTH);
        this.month = c.get(Calendar.MONTH) + 1; //since calendar.month goes from 0 (jan) to 11 (dec)
    }

    /**
     * Constructor for Date class
     * @param date
     */
    public Date(String date) {
        this.month = Integer.parseInt(date.substring(0,date.indexOf("/")));
        this.day = Integer.parseInt(date.substring(date.indexOf("/")+1,date.lastIndexOf("/")));
        this.year =  Integer.parseInt(date.substring(date.lastIndexOf("/")+1));
    }

    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int January = 1;
    public static final int February = 2;
    public static final int March = 3;
    public static final int April = 4;
    public static final int May = 5;
    public static final int June = 6;
    public static final int July = 7;
    public static final int August = 8;
    public static final int September = 9;
    public static final int October = 10;
    public static final int November = 11;
    public static final int December = 12;

    /**
     * isLeapYear method for checking if a year is a leap year
     * @return true if the year is a leap year, false if not
     */
    public boolean isLeapYear() {
        if((year % QUADRENNIAL) == 0){
            if((year % CENTENNIAL)==0){
                if((year % QUATERCENTENNIAL)==0){
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * isSixteen method for checking if a person is 16 years old or older
     * @return true if the person is 16 years old or older, false if not
     */
    public boolean isSixteen() {
        int age = c.get(Calendar.YEAR) - this.year;
        boolean tf;
        if(this.month > c.get(Calendar.MONTH)+1){
            age--;
        }
        if(this.month == c.get(Calendar.MONTH)+1){
            if(this.day > c.get(Calendar.DAY_OF_MONTH)){
                age--;
            }
        }
        return age >= 16;

    }

    /**
     * daysValidForMonth method for checking if the day is valid for the month
     * @return true if the day is valid for the month, false if not
     */
    private boolean daysValidForMonth(){

        if(day < 1 || day > 31) return false;

        if(month == January || month == March || month == May
                || month == July || month == August || month == October || month == December) {
            return day <= 31;
        }
        if(month == April || month == June || month == September || month == November) {
            return day <= 30;
        }
        if(month == February) {
            if(isLeapYear()) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }
        return true;
    }

    /**
     * isValid method for checking if the date is valid
     * @return true if the date is valid, false if not
     */
    public boolean isValid() {
        if(month > 12 || month < 1) {
            return false;
        } else {
            if(!daysValidForMonth()) return false;
        }
        if(year < c.get(Calendar.YEAR)){
            return true;
        } else if (year > c.get(Calendar.YEAR)){
            return false;
        } else {
            if(this.month < (c.get(Calendar.MONTH)+1)){
                return true;
            } else if (this.month > (c.get(Calendar.MONTH)+1)){
                return false;
            } else {
                return (this.day <= (c.get(Calendar.DAY_OF_MONTH)));
            }
        }
    }
}