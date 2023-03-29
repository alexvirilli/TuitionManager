package com.example.project3gui;


public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    //constructors
    public EnrollStudent(){
        this.profile = null;
        this.creditsEnrolled = 0;
    }

    /**
     * Create an enrolled student with the given profile and credits enrolled
     * @param profile
     * @param creditsEnrolled
     */
    public EnrollStudent(Profile profile, int creditsEnrolled){
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    //setter
    public void setCreditsEnrolled(int creditsEnrolled){
        this.creditsEnrolled = creditsEnrolled;
    }

    //getters
    public int getCreditsEnrolled(){
        return creditsEnrolled;
    }

    public Profile getProfile(){
        return profile;
    }

    /**
     * Compare two enroll students
     * @param enrollStudent
     * @return true if the enrolled students are equal, false otherwise
     */
    @Override
    public boolean equals(Object enrollStudent){
        if(this == enrollStudent) return true;
        if(!(enrollStudent instanceof EnrollStudent)) return false;
        EnrollStudent e = (EnrollStudent) enrollStudent;
        return this.profile.equals(e.profile);
    }

    /**
     * Return a string representation of the enrolled student
     */
    @Override
    public String toString(){
        return this.profile.toString() + " enrolled " + creditsEnrolled + " credits";
    }

}