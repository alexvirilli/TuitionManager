package com.example.project3gui;


/**
 * This enum class contains the major codes and the school where the major exists.
 * @Author: Alex Virilli, Ryan Elizondo-Fallas
 */
public enum Major {
    //declare enum types
    CS("01:198","SAS"), MATH("01:640","SAS"),
    EE("14:332","SOE"), ITI("04:547","SC&I"),
    BAIT("33:136","RBS");

    //private variables to store the major codes and school where the majors exist
    private final String majorCode;
    private final String school;

    /**
     * This constructor creates a major object
     * @param majorCode
     * @param school
     */
    private Major(String majorCode, String school){
        this.majorCode = majorCode;
        this.school = school;
    }

    //getter methods
    public String getMajorCode() {
        return majorCode;
    }
    public String getSchool() {
        return school;
    }

    /**
     * This method returns the name of the major
     * @return the name of the major
     */
    public String getName(){
        if(this.majorCode.equals("01:198")) return "CS";
        if(this.majorCode.equals("01:640")) return "MATH";
        if(this.majorCode.equals("14:332")) return "EE";
        if(this.majorCode.equals("04:547")) return "ITI";
        if(this.majorCode.equals("33:136")) return "BAIT";
        return null;
    }

    /**
     * This method returns the index of the major in the enum
     * @param major
     * @return the index of the major in the enum
     */
    public int getMajorIndex(String major){
        if(major.equalsIgnoreCase("CS")) return 0;
        if(major.equalsIgnoreCase("MATH")) return 1;
        if(major.equalsIgnoreCase("EE")) return 2;
        if(major.equalsIgnoreCase("ITI")) return 3;
        if(major.equalsIgnoreCase("BAIT")) return 4;
        return -1;
    }

}