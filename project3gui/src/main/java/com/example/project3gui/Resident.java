package com.example.project3gui;


public class Resident extends Student{
    private int scholarship;

    public static final int FULL_TIME_LOWER_BOUND = 12;
    public static final int FULL_TIME_UPPER_BOUND = 16;
    public static final double UNIVERSITY_FEE_FULL_TIME = 3268;
    public static final double UNIVERSITY_FEE_PART_TIME = 3268 * 0.8;
    public static final double COST_PER_CREDIT_HOUR = 404;
    public static final double TUITION = 12536;

    /**
     * Create a resident student with the given profile, major, credit completed, and scholarship
     * @param profile
     * @param major
     * @param creditCompleted
     * @param scholarship
     */
    public Resident(Profile profile, Major major, int creditCompleted, int scholarship){
        super(profile, major, creditCompleted);
        this.scholarship = scholarship;
    }

    public void setScholarship(int scholarship){
        this.scholarship = scholarship;
    }

    /**
     * tuitionDue calculates the tuition due for resident student using the given credits enrolled, scholarship, and tuition
     * @param creditsEnrolled
     * @return the tuition due for resident student
     */
    @Override
    public double tuitionDue(int creditsEnrolled){
        if(creditsEnrolled < FULL_TIME_LOWER_BOUND){
            return creditsEnrolled * COST_PER_CREDIT_HOUR + UNIVERSITY_FEE_PART_TIME - scholarship;
        }
        if(creditsEnrolled > FULL_TIME_UPPER_BOUND){
            int extraCredits = creditsEnrolled - FULL_TIME_UPPER_BOUND;
            double extraCredits_cost = extraCredits * COST_PER_CREDIT_HOUR;
            return TUITION + UNIVERSITY_FEE_FULL_TIME + extraCredits_cost - scholarship;
        }
        return TUITION + UNIVERSITY_FEE_FULL_TIME - scholarship;

    }

    /**
     * isResident checks if the student is a resident
     * @return true
     */
    @Override
    public boolean isResident(){
        return true;
    }

    @Override
    public String toString(){
        return this.getProfile().toString() + " (" + this.getMajor().getMajorCode() + " " +
                getMajor().getName() + " " + getMajor().getSchool() + ") credits completed: " +
                getCreditCompleted() + " (" + getGrade() + ")(resident)";
    }

    @Override
    public String printType(){
        return "(Resident)";
    }

}