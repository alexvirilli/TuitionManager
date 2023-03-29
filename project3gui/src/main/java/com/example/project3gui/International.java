package com.example.project3gui;


public class International extends NonResident{

    private boolean isStudyAbroad;
    public static final int INTERNATIONAL_MINIMUM_CREDITS = 12;
    public static final int INTERNATIONAL_STUDY_ABROAD_MAXIMUM = 12;
    public static final int TUITION_INTERNATIONAL = 29737;
    public static final int UNIVERSITY_FEE = 3268;
    public static final int HEALTH_INSURANCE_FEE = 2650;

    /**
     * Create an international student with the given profile, major, credit completed, and study abroad status
     * @param profile
     * @param major
     * @param creditCompleted
     * @param isStudyAbroad
     */
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad){
        super(profile,major,creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    //getter
    public boolean getIsStudyAbroad(){
        return isStudyAbroad;
    }

    /**
     * isValid checks if the credit enrolled is valid for international student
     * @param creditEnrolled
     * @return true if the credit enrolled is valid for international student
     */
    @Override
    public boolean isValid(int creditEnrolled){
        if(isStudyAbroad) return creditEnrolled <= 12;
        return creditEnrolled >= 12 && creditEnrolled <=24;
    }

    /**
     * isResident checks if the student is a resident
     * @return false
     */
    @Override
    public boolean isResident(){
        return false;
    }

    /**
     * tuitionDue calculates the tuition due for international student, using the given credits enrolled, and tuition, and study abroad status
     * @param creditEnrolled
     * @return the tuition due for international student
     */
    @Override
    public double tuitionDue(int creditEnrolled){
        if(isStudyAbroad) return UNIVERSITY_FEE + HEALTH_INSURANCE_FEE;
        return TUITION_INTERNATIONAL + UNIVERSITY_FEE + HEALTH_INSURANCE_FEE;
    }

    private String getStudyAbroadString(){
        if(isStudyAbroad){
            return "(international: study abroad)";
        } else {
            return "(international)";
        }
    }

    @Override
    public String toString(){
        return this.getProfile().toString() + " (" + this.getMajor().getMajorCode() + " " +
                getMajor().getName() + " " + getMajor().getSchool() + ") credits completed: " +
                getCreditCompleted() + " (" + getGrade() + ")(non-resident)" + getStudyAbroadString();
    }

    @Override
    public String printType(){
        if(!isStudyAbroad) {
            return "(International student)";
        } else {
            return "(International student study abroad)";
        }
    }



}