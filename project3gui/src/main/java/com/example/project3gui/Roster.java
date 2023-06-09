
package com.example.project3gui;


import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Roster.java
 *
 * This class represents a roster of students. It contains methods to add, remove, and change students.
 * It also contains methods to print the roster in different ways.
 *
 * @author Alex Virilli, Ryan Elizondo-Fallas
 *
 */
public class Roster {
    int NOT_FOUND = -1;
    private Student[] roster;
    private int size;

    public Roster(){
        this.size = 4;
        this.roster = new Student[size];
    }
    /**
     * find method for finding a student in the roster
     * @param student the student to be found
     * @return the index of the student if found, -1 if not found
     */
    private int find(Student student){
        for(int i = 0; i < size; i++){
            if(roster[i] == null) return NOT_FOUND;
            if(roster[i].equals(student)) return i;
        }
        return NOT_FOUND;
    }

    /**
     * getStudent method for getting a student from the roster
     * @param student
     * @return the student if found, null if not found
     */
    public Student getStudent(Student student){ //returns student if found, null otherwise
        int index = find(student);
        return roster[index];
    }
    /**
     * grow method for increasing the capacity of the roster by 4
     */
    private void grow(){
        int tempSize = size;
        size+=4;
        Student[] tempArr = new Student[size];
        for(int i = 0; i < tempSize; i++){
            tempArr[i] = roster[i];
        }
        roster = tempArr;
    }

    /**
     * add method for adding a student to the roster
     * @param student
     * @return true if student is added, false if not
     */
    public boolean add(Student student){
        if(contains(student)) return false;
        if(roster[size-1]!=null) grow();
        int i = 0;
        while(roster[i]!=null){
            i++;
        }
        roster[i] = student;
        return true;
    }

    /**
     * contains method for checking if a student is in the roster
     * @param student
     * @return true if student is in roster, false if not
     */
    public boolean contains(Student student){ //returns true if student is in roster, false otherwise
        for(int i = 0; i < size; i++){
            if(roster[i]==null) return false;
            if(roster[i].equals(student)) return true;
        }
        return false;
    }

    /**
     * changeMajor method for changing a student's major
     * @param student
     * @param major
     */
    public void changeMajor(Student student, Major major){
        if(!contains(student)) return; //if student is not in roster, do nothing
        int index = find(student); //find index of student
        roster[index].setMajor(major); //change major
    }
    /**
     * remove method for removing a student from the roster
     * @param student
     * @return true if student is removed, false if not
     */
    public boolean remove(Student student){ //remove student from roster
        if(!contains(student)) return false;
        if(find(student)==-1) return false;
        int index = find(student);
        for(int i = index; i < size; i++){
            if(i+1<size && roster[i+1]==null){
                roster[i] = null;
                return true;
            }
            if(i+1 < size) roster[i] = roster[i+1];
        }
        roster[size-1] = null;
        return true;
    }

    /**
     * Sorts the roster lexicographically by last name, first name, and DOB
     */
    public void lexicoSort(){
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(roster[i]==null || roster[j]==null) break;
                if(roster[i].compareTo(roster[j])>0){
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }
    }

    private int countStudentsInSchool(String school){
        int count = 0;
        int i = 0;
        while (i != size && roster[i] != null) {
            if (roster[i].getMajor().getSchool().equalsIgnoreCase(school))
                count++;
            if (i < size) i++;
        }
        return count;
    }

    /*/*
     * printSchool method for printing all students in a school
     * @param school
     **/
    public void printSchool(String school, TextArea messageArea){ //prints students in a school
        lexicoSort();
        int i = 0;
        if(roster[0]!=null) {
            if (schoolExists(school)) {
                if(countStudentsInSchool(school)!=0){
                messageArea.appendText("* Students in " + school + " *\n");
                while (i != size && roster[i] != null) {
                    if (roster[i].getMajor().getSchool().equalsIgnoreCase(school))
                        messageArea.appendText(roster[i].toString() + "\n");
                    if (i < size) i++;
                }
                messageArea.appendText("* end of list **\n");
            } else {
                messageArea.appendText(school + " is empty!\n");
            }
        } else {
                messageArea.appendText(school + " does not exist!\n");
            }
        } else {
            messageArea.appendText("Student roster is empty!\n");
        }

    }
    /**
     * schoolExists method for checking if a school exists
     * @param school
     * @return true if school exists, false if not
     */
    private boolean schoolExists(String school){ //checks if school exists
        if(school.equalsIgnoreCase("SAS")) return true;
        if(school.equalsIgnoreCase("RBS")) return true;
        if(school.equalsIgnoreCase("SOE")) return true;
        if(school.equalsIgnoreCase("SC&I")) return true;
        return false;
    }

    /**
     * print method for printing the roster
     *
     */
    public void print(TextArea messageArea){ //prints roster
        //sort lexicographically
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(roster[i]==null || roster[j]==null) break;
                if(roster[i].compareTo(roster[j])>0){
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }
        //print from 0 to size-1
        int i = 0;
        if(roster[i]==null){
            messageArea.appendText("Student roster is empty!\n");
        } else {
            messageArea.appendText("* Student roster sorted by last name, first name, DOB **\n");
            while (i != size && roster[i] != null) {
                messageArea.appendText(roster[i].toString() + "\n");
                if (i < size) i++;
            }
            messageArea.appendText("* end of roster **\n");
        }
    }

    private void printForStanding(TextArea messageArea){ //prints roster
        //print from 0 to size-1
        int i = 0;
        if(roster[i]==null){
            messageArea.appendText("Student roster is empty!\n");
        } else {
            messageArea.appendText("* Student roster sorted by standing **\n");
            while (i != size && roster[i] != null) {
                messageArea.appendText(roster[i].toString() + "\n");
                if (i < size) i++;
            }
            messageArea.appendText("* end of roster **\n");
        }
    }
    /**
     * printSequential method for printing the roster sequentially
     */
    private void printSequential(TextArea messageArea){
        int i = 0;
        if(roster[i]==null){
            messageArea.appendText("Student roster is empty!\n");
        } else {
            messageArea.appendText("* Student roster sorted by school, major **\n");
            while (i != size && roster[i] != null) {
                messageArea.appendText(roster[i].toString() + "\n");
                if (i < size) i++;
            }
            messageArea.appendText("* end of roster **\n");
        }
    }

    /**
     * printBySchoolMajor method for printing the roster by school and major
     */
    public void printBySchoolMajor(TextArea messageArea){
        //sort by school
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(roster[i]==null || roster[j]==null) break;
                if(roster[i].getMajor().getSchool().compareTo(roster[j].getMajor().getSchool())>0){
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }
        //sort by major WITHIN school
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(roster[i]==null || roster[j]==null) break;
                if(roster[i].getMajor().getMajorCode().compareTo(roster[j].getMajor().getMajorCode())>0 &&
                        roster[i].getMajor().getSchool().compareTo(roster[j].getMajor().getSchool())==0){
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }
        //sort by name within school and major
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(roster[i]==null || roster[j]==null) break;
                if(roster[i].compareTo(roster[j])>0 &&
                        roster[i].getMajor().getSchool().compareTo(roster[j].getMajor().getSchool())==0 &&
                        roster[i].getMajor().getMajorCode().compareTo(roster[j].getMajor().getMajorCode())==0){
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }
        //print array sequentially
        printSequential(messageArea);
    }

    /**
     * printByStanding method for printing the roster by credit standing
     */
    public void printByStanding(TextArea messageArea){
        //sort by standing (credit completed)
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(roster[i]==null || roster[j]==null) break;
                if(roster[i].getCreditCompleted() < roster[j].getCreditCompleted()){
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }
        //sort by profile if two students have the same credits
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(roster[i]==null || roster[j]==null) break;
                if(roster[i].compareTo(roster[j])>0 && roster[i].getCreditCompleted() == roster[j].getCreditCompleted()){
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }
        //print sequentially
        printForStanding(messageArea);
    }

    /**
     * printCompleted method for printing the roster of students who have completed 120 credits or more
     */
    public  void printCompleted(Enrollment enrollment, TextArea messageArea){
        int i = 0;
        if(roster[i]==null){
            messageArea.appendText("Student roster is empty!\n");
        } else {
            messageArea.appendText("Credit completed has been updated\n");
            messageArea.appendText("** list of students eligible for graduation **\n");
            while (i != size && roster[i] != null) {
                Profile profile = roster[i].getProfile();
                EnrollStudent enrollStudent = new EnrollStudent(profile,0);
                if(enrollment.contains(enrollStudent)){
                    int currentCredits = roster[i].getCreditCompleted();
                    EnrollStudent student = enrollment.getEnrollStudent(enrollStudent);
                    currentCredits += student.getCreditsEnrolled();
                    roster[i].setCreditCompleted(currentCredits);
                }
                if(roster[i].getCreditCompleted()>=120) messageArea.appendText(roster[i].toString() + "\n");
                if (i < size) i++;
            }
        }
    }
}