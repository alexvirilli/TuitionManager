package com.example.project3gui;


public class NonResident extends Student{

    public static final int FULL_TIME_LOWER_BOUND = 12;
    public static final int FULL_TIME_UPPER_BOUND = 16;
    public static final double TUITION_NON_RESIDENT = 29737;
    public static final double UNIVERSITY_FEE_FULL_TIME = 3268;
    public static final double UNIVERSITY_FEE_PART_TIME = 3268 * 0.8;
    public static final double COST_PER_CREDIT_HOUR_NON_RESIDENT = 966;

    /**
     * Create a non-resident student with the given profile, major, and credit completed
     * @param profile
     * @param major
     * @param creditCompleted
     */
    public NonResident(Profile profile, Major major, int creditCompleted){
        super(profile, major, creditCompleted);
    }

    /**
     * tuitionDue calculates the tuition due for non-resident student, using the given credits enrolled, and tuition
     * @param creditsEnrolled
     * @return the tuition due for non-resident student
     */
    @Override
    public double tuitionDue(int creditsEnrolled){
        if(creditsEnrolled < FULL_TIME_LOWER_BOUND){
            return (creditsEnrolled*COST_PER_CREDIT_HOUR_NON_RESIDENT) + UNIVERSITY_FEE_PART_TIME;
        }
        if(creditsEnrolled > FULL_TIME_UPPER_BOUND){
            int extraCredits = creditsEnrolled - FULL_TIME_UPPER_BOUND;
            double extraCredits_cost = extraCredits * COST_PER_CREDIT_HOUR_NON_RESIDENT;
            return TUITION_NON_RESIDENT + UNIVERSITY_FEE_FULL_TIME + extraCredits_cost;
        }
        return TUITION_NON_RESIDENT + UNIVERSITY_FEE_FULL_TIME;
    }

    /**
     * isResident checks if the student is a resident
     * @return false
     */
    @Override
    public boolean isResident(){
        return false;
    }

    @Override
    public String toString(){
        return this.getProfile().toString() + " (" + this.getMajor().getMajorCode() + " " +
                getMajor().getName() + " " + getMajor().getSchool() + ") credits completed: " +
                getCreditCompleted() + " (" + getGrade() + ")(non-resident)";
    }

    @Override
    public String printType(){
        return "(Non-Resident)";
    }

}