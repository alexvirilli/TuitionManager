package com.example.project3gui;


public abstract class Student implements Comparable<Student>{

    private Profile profile;
    private Major major;
    private int creditCompleted;

    public Student(){
        this.profile = null;
        this.major = null;
        this.creditCompleted = 0;
    }

    /**
     * Create a student with the given profile, major, and credit completed
     * @param profile
     * @param major
     * @param creditCompleted
     */
    public Student(Profile profile, Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }
    public Profile getProfile(){
        return this.profile;
    }
    public Major getMajor(){
        return this.major;
    }
    public int getCreditCompleted(){
        return this.creditCompleted;
    }

    //setters
    public void setMajor(Major major) { //change major
        this.major = major;
    }

    public void setCreditCompleted(int creditCompleted){
        this.creditCompleted = creditCompleted;
    }

    /**
     * compareTo compares two students
     * @param student
     * @return 1 if the first student is greater than the second student, -1 if the first student
     * is less than the second student, 0 if the two students are equal
     */
    @Override
    public int compareTo(Student student) {
        if((this.profile.compareTo(student.getProfile())) > 0)
            return 1;
        if((this.profile.compareTo(student.getProfile())) < 0)
            return -1;
        return 0;
    }

    /**
     * equals compares two students
     * @param student
     * @return true if the two students are equal, false otherwise
     */
    @Override
    public boolean equals(Object student) {
        if(this == student) return true;
        if(!(student instanceof Student)) return false;
        Student s = (Student) student;
        if(this.getProfile().equals(s.getProfile())) return true;
        //if(this.compareTo(s)==0) return true;
        return false;
    }

    /**
     * Return a string representation of the student
     */
    @Override
    public String toString(){
        return profile.toString() + " (" + major.getMajorCode() + " " + major.getName() + " " + major.getSchool() + ") credits completed: " + creditCompleted
                + " (" + getGrade() + ")";
    }

    /**
     * getGrade returns the class of the student
     * @return the class of the student
     */
    public String getGrade(){
        if(this.creditCompleted < 30) return "Freshman";
        if(this.creditCompleted < 60) return "Sophomore";
        if(this.creditCompleted < 90) return "Junior";
        return "Senior";
    }

    /**
     * isValid checks if the number of credits enrolled is valid
     * @param creditEnrolled
     * @return true if the number of credits enrolled is valid, false otherwise
     */
    public boolean isValid(int creditEnrolled){
        return (creditEnrolled >= 3 && creditEnrolled <= 24);
    }

    public abstract double tuitionDue(int creditsEnrolled);
    public abstract boolean isResident();

    public abstract String printType();

}