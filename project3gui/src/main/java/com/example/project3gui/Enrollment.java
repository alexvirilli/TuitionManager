package com.example.project3gui;

import javafx.scene.control.TextArea;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public Enrollment(){
        this.size = 4;
        this.enrollStudents = new EnrollStudent[size];
    }

    /**
     * private method to grow the array by 4
     */
    private void grow(){
        int tempSize = size;
        size+=4;
        EnrollStudent[] tempArr = new EnrollStudent[size];
        for(int i = 0; i < tempSize; i++){
            tempArr[i] = enrollStudents[i];
        }
        enrollStudents = tempArr;
    }

    /**
     * Add an enrolled student to the end of the array
     * @param enrollStudent
     */
    public void add(EnrollStudent enrollStudent){
        if(contains(enrollStudent)) return;
        if(!contains(enrollStudent)) {
            if (enrollStudents[size - 1] != null) grow();
            int i = 0;
            while (enrollStudents[i] != null) {
                i++;
            }
            enrollStudents[i] = enrollStudent;
        }
    }

    /**
     * Search through the array to find the enrolled student
     * @param enrollStudent
     * @return the index of the enrolled student, -1 if not found
     */
    private int getIndex(EnrollStudent enrollStudent){
        int index = 0;
        while(enrollStudents[index]!=null && index < size-1){
            if(enrollStudents[index].equals(enrollStudent)) return index;
            index++;
        }
        return -1;
    }

    /**
     * private method to find the last index of the array
     * @return the last index of the array
     */
    private int getLastIndex(){
        int index = 0;
        int lastIndex = size-1;
        if(enrollStudents[lastIndex]!=null) return (lastIndex);
        while(enrollStudents[index]!=null){
            index++;
        }
        return index;
    }

    /**
     * Remove an enrolled student from the array by moving the last student in the array to replace the index of
     * the deleted student
     * @param enrollStudent
     */
    public void remove(EnrollStudent enrollStudent){
        if(!contains(enrollStudent)) return;
        int removeIndex = getIndex(enrollStudent);
        int lastIndex = getLastIndex();
        enrollStudents[removeIndex] = enrollStudents[lastIndex];
        enrollStudents[lastIndex] = null;
    }

    /**
     * contains method to check if an enrolled student is in the array
     * @param enrollStudent
     * @return true if the enrolled student is in the array, false otherwise
     */
    public boolean contains(EnrollStudent enrollStudent){
        int NOT_FOUND = -1;
        return (!(getIndex(enrollStudent) == NOT_FOUND));
    }

    public EnrollStudent getEnrollStudent(EnrollStudent enrollStudent){
        return enrollStudents[getIndex(enrollStudent)];
    }

    /**
     * print the array as is without sorting
     */
    public void print(TextArea messageArea){
        int index = 0;
        if (enrollStudents[index] == null) {
            messageArea.appendText("Enrollment is empty!\n");
        } else {
            messageArea.appendText("** Enrollment **\n");
            while(enrollStudents[index]!=null && index < size){
                messageArea.appendText(enrollStudents[index].toString() + "\n");
                index++;
            }
            messageArea.appendText("* end of enrollment *\n");
        }
    }

    public void printTuitionDue(Roster roster, TextArea messageArea){
        int index = 0;
        if (enrollStudents[index] == null) {
            messageArea.appendText("Enrollment is empty!\n");
        } else {
            messageArea.appendText("** Tuition Due **\n");
            while(enrollStudents[index]!=null && index < size){
                //create a pointer object to return the student type
                Profile profile = enrollStudents[index].getProfile();
                NonResident pointer = new NonResident(profile, Major.CS, 0);
                Student student = roster.getStudent(pointer);
                //print out info
                double tuitionDue = student.tuitionDue(enrollStudents[index].getCreditsEnrolled());
                messageArea.appendText(profile.toString() + " " + student.printType() + " enrolled " + enrollStudents[index].getCreditsEnrolled() +" credits, tuition due: $" + String.format("%.2f",tuitionDue) + "\n");
                index++;
            }
            messageArea.appendText("* end of tuition due *\n");
        }
    }

}