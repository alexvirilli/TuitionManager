package com.example.project3gui;


public class TriState extends NonResident{
    private String state;

    /**
     * Create a TriState student with the given profile, major, credit completed, and state
     * @param profile
     * @param major
     * @param creditCompleted
     * @param state
     */
    public TriState(Profile profile, Major major, int creditCompleted, String state){
        super(profile, major, creditCompleted);
        this.state = state;
    }

    /**
     * getDiscount returns the discount for TriState students depending on the state
     * @return the discount for TriState students
     */
    private int getDiscount(){
        if(state.equalsIgnoreCase("NY")) return 4000;
        if(state.equalsIgnoreCase("CT")) return 5000;
        return 0;
    }

    /**
     * tuitionDue calculates the tuition due for TriState students depending on the credit enrolled, state, and discount
     * @param creditEnrolled
     * @return the tuition due for TriState students
     */
    @Override
    public double tuitionDue(int creditEnrolled){
        if(creditEnrolled < FULL_TIME_LOWER_BOUND){
            return creditEnrolled*COST_PER_CREDIT_HOUR_NON_RESIDENT + UNIVERSITY_FEE_PART_TIME;
        }
        if(creditEnrolled > FULL_TIME_UPPER_BOUND){
            int extraCredits = creditEnrolled - FULL_TIME_UPPER_BOUND;
            double extraCredits_cost = extraCredits * COST_PER_CREDIT_HOUR_NON_RESIDENT;
            return extraCredits_cost + UNIVERSITY_FEE_FULL_TIME + TUITION_NON_RESIDENT - getDiscount();
        }
        return TUITION_NON_RESIDENT + UNIVERSITY_FEE_FULL_TIME - getDiscount();
    }

    /**
     * isResident checks if the student is a resident
     * @return false
     */
    @Override
    public boolean isResident(){
        return false;
    }

    private String triStateString(){
        if(state.equalsIgnoreCase("NY")){
            return "(tri-state:NY)";
        } else {
            return "(tri-state:CT)";
        }

    }

    @Override
    public String toString(){
        return this.getProfile().toString() + " (" + this.getMajor().getMajorCode() + " " +
                getMajor().getName() + " " + getMajor().getSchool() + ") credits completed: " +
                getCreditCompleted() + " (" + getGrade() + ")(non-resident)" + triStateString();
    }

    @Override
    public String printType(){
        if(state.equalsIgnoreCase("NY")) {
            return "(Non-Resident)(Tri-State: NY)";
        } else {
            return "(Non-Resident)(Tri-State: CT)";
        }
    }

}