package com.example.project3gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TuitionManagerController {

    @FXML
    private TextField enrollDrop_credits;

    @FXML
    private DatePicker enrollDrop_dob;

    @FXML
    private TextField enrollDrop_fname;

    @FXML
    private TextField enrollDrop_lname;

    @FXML
    private CheckBox isStudyAbroad;

    @FXML
    private ToggleGroup major;

    @FXML
    private TextArea messageArea;

    @FXML
    private ToggleGroup nonres_subgroup;

    @FXML
    private RadioButton rb_bait;

    @FXML
    private RadioButton rb_cs;

    @FXML
    private RadioButton rb_ct;

    @FXML
    private RadioButton rb_ece;

    @FXML
    private RadioButton rb_international;

    @FXML
    private RadioButton rb_iti;

    @FXML
    private RadioButton rb_math;

    @FXML
    private RadioButton rb_nonResident;

    @FXML
    private RadioButton rb_ny;

    @FXML
    private RadioButton rb_resident;

    @FXML
    private RadioButton rb_triState;

    @FXML
    private MenuItem semesterEnd;

    @FXML
    private ToggleGroup res_nonres;

    @FXML
    private TextField roster_creditCompleted;

    @FXML
    private DatePicker roster_dob;

    @FXML
    private TextField roster_fname;

    @FXML
    private TextField roster_lname;

    @FXML
    private TextField scholarship_amount;

    @FXML
    private DatePicker scholarship_dob;

    @FXML
    private TextField scholarship_fname;

    @FXML
    private TextField scholarship_lname;

    @FXML
    private ToggleGroup state;

    Roster roster = new Roster();
    Enrollment enrollment = new Enrollment();

    private boolean isValidToAdd() {
        Date date = new Date(roster_dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if (date.isValid()) {
            if (date.isSixteen()) {
                    if (isInt(roster_creditCompleted.getText())) {
                        if (Integer.parseInt(roster_creditCompleted.getText()) >= 0) {
                            return true;
                        } else {
                            messageArea.appendText("Credits completed invalid: cannot be negative!\n");
                        }
                    } else {
                        messageArea.appendText("Credits completed invalid: not an integer!\n");
                    }
            } else {
               messageArea.appendText("DOB invalid: " + date.toString() + " younger than 16 years old.\n");
            }
        } else {
            messageArea.appendText("DOB invalid: " + date.toString() + " not a valid calendar date!\n");
        }
        return false;
    }

    private boolean isValidToAdd(String[] line) {
        Date date = new Date(line[3]);
        if (date.isValid()) {
            if (date.isSixteen()) {
                if (Major.CS.getMajorIndex(line[4]) != -1) {
                    if (isInt(line[5])) {
                        if (Integer.parseInt(line[5]) >= 0) {
                            return true;
                        } else {
                            messageArea.appendText("Credits completed invalid: cannot be negative!\n");
                        }
                    } else {
                        messageArea.appendText("Credits completed invalid: not an integer!\n");
                    }
                } else {
                    messageArea.appendText("Major code invalid: " + line[4] + "\n");
                }
            } else {
                messageArea.appendText("DOB invalid: " + date.toString() + " younger than 16 years old.\n");
            }
        } else {
            messageArea.appendText("DOB invalid: " + date.toString() + " not a valid calendar date!\n");
        }
        return false;
    }

    private void addMethod(){
        String fname = roster_fname.getText(); String lname = roster_lname.getText();
        String date = roster_dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")); Date dob = new Date(date);
        int creditCompleted = Integer.parseInt(roster_creditCompleted.getText());
        Profile profile = new Profile(lname,fname,dob); Major major;
        if(rb_bait.isSelected()){
            major = Major.BAIT; } else if (rb_cs.isSelected()) {
            major = Major.CS; } else if (rb_ece.isSelected()) {
            major = Major.EE; } else if (rb_iti.isSelected()) {
            major = Major.ITI; } else /* math */ {
            major = Major.MATH; }
        if(rb_resident.isSelected()){
            int scholarship = 0; Resident resident = new Resident(profile,major,creditCompleted,scholarship);
            if(roster.add(resident)) {
                messageArea.appendText(resident.toString() + " added to the roster\n"); } else {
                messageArea.appendText(resident.toString() + " is already in the roster\n"); }
        } else if (rb_nonResident.isSelected()) {
            NonResident nonResident = new NonResident(profile,major,creditCompleted);
            if(roster.add(nonResident)) {
                messageArea.appendText(nonResident.toString() + " added to the roster\n"); } else {
                messageArea.appendText(nonResident.toString() + " is already in the roster\n"); }
        } else if (rb_triState.isSelected()) {
            String state;
            if(rb_ny.isSelected()){
                state = "NY"; } else {
                state = "CT"; }
            TriState triState = new TriState(profile, major,creditCompleted,state);
            if(roster.add(triState)) {
                messageArea.appendText(triState.toString() + " added to the roster\n"); } else {
                messageArea.appendText(triState.toString() + " is already in the roster\n"); }
        } else /*international*/ {
            boolean studyAbroad; studyAbroad = isStudyAbroad.isSelected();
            International international = new International(profile,major,creditCompleted,studyAbroad);
            if(roster.add(international)) {
                messageArea.appendText(international.toString() + " added to the roster\n"); } else {
                messageArea.appendText(international.toString() + " is already in the roster\n"); } }
        roster_fname.setText(""); roster_lname.setText(""); roster_creditCompleted.setText(""); roster_dob.setValue(null);
    }

    private boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException n) {
            return false;
        }
    }

    @FXML
    void add(ActionEvent event) {
        if(roster_fname.getText().trim().equals("") || roster_lname.getText().trim().equals("")
        || roster_creditCompleted.getText().trim().equals("") || roster_dob.getValue()==null){
            messageArea.appendText("Please fill out all text fields\n");
        } else if (roster_fname.getText().contains(" ") || roster_lname.getText().contains(" ")) {
            messageArea.appendText("First or last names cannot contains spaces\n");
        } else if (!isInt(roster_creditCompleted.getText()) || Integer.parseInt(roster_creditCompleted.getText()) < 0) {
            messageArea.appendText("Credit completed invalid\n");
        } else if (roster_dob.getValue()==null){
            messageArea.appendText("Date of birth cannot be empty\n");
        } else {
            if(isValidToAdd()) addMethod();
        }
    }

    private void changeMajorMethod(){
        String fname = roster_fname.getText();
        String lname = roster_lname.getText();
        String date = roster_dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date dob = new Date(date);
        Profile profile = new Profile(lname,fname,dob);
        Resident pointer = new Resident(profile, Major.CS, 0, 0);

        Major major;
        if(rb_bait.isSelected()) {
            major = Major.BAIT;
        } else if(rb_cs.isSelected()) {
            major = Major.CS;
        } else if(rb_ece.isSelected()) {
            major = Major.EE;
        } else if(rb_iti.isSelected()) {
            major = Major.ITI;
        } else /*math*/ {
            major = Major.MATH;
        }

        if(roster.contains(pointer)){
            Student student = roster.getStudent(pointer);
            student.setMajor(major);
            messageArea.appendText(profile.toString() + " major changed to " + major.getName() + "\n");
        } else {
            messageArea.appendText(profile.toString() + " not in the roster\n");
        }
        roster_fname.setText(""); roster_lname.setText(""); roster_creditCompleted.setText(""); roster_dob.setValue(null);
    }
    @FXML
    void changeMajor(ActionEvent event) {
        if(roster_fname.getText().trim().equals("") || roster_lname.getText().trim().equals("")
                || roster_dob.getValue()==null){
            messageArea.appendText("Please fill out all text fields\n");
        } else if (roster_fname.getText().contains(" ") || roster_lname.getText().contains(" ")) {
            messageArea.appendText("First or last names cannot contains spaces\n");
        } else {
            changeMajorMethod();
        }
    }

    private void enrollMethod(){
        boolean add = true; String fname = enrollDrop_fname.getText(); String lname = enrollDrop_lname.getText();
        String date = enrollDrop_dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date dob = new Date(date); Profile profile = new Profile(lname,fname,dob);
        int enrolledCredits = Integer.parseInt(enrollDrop_credits.getText());
        Resident test = new Resident(profile,Major.CS,0,0);
        if(!(roster.contains(test))){
            messageArea.appendText(profile.toString() + " not in the roster\n");
        } else {
            EnrollStudent enrollStudent = new EnrollStudent(profile, enrolledCredits);
            Student student = roster.getStudent(test);
            if(student instanceof International){
                if(!student.isValid(enrolledCredits)){
                    if(((International) student).getIsStudyAbroad()){
                        messageArea.appendText("(International student study abroad) " + enrolledCredits + ": Invalid credit hours\n"); add=false;
                    } else {
                        messageArea.appendText("(International student) " + enrolledCredits + ": Invalid credit hours\n"); add=false; }}
            } else if (student instanceof Resident) {
                if(!student.isValid(enrolledCredits)){
                    messageArea.appendText("(Resident) " + enrolledCredits + ": Invalid credit hours\n"); add=false; }
            } else if (student instanceof TriState) {
                if(!student.isValid(enrolledCredits)){
                    messageArea.appendText("(Tri-State) "+ enrolledCredits + ": Invalid credit hours\n"); add=false; }
            } else if(student instanceof NonResident){
                if(!student.isValid(enrolledCredits)){
                    messageArea.appendText("(Non-Resident) " + enrolledCredits + ": Invalid credit hours\n"); add=false; } }
            if(enrollment.contains(enrollStudent) && add){
                EnrollStudent pointer = enrollment.getEnrollStudent(enrollStudent);
                pointer.setCreditsEnrolled(enrolledCredits);
                messageArea.appendText(enrollStudent.toString() + "\n");
            } else if(add){
                enrollment.add(enrollStudent);
                messageArea.appendText(enrollStudent.toString() + "\n");}
        } enrollDrop_credits.setText(""); enrollDrop_fname.setText(""); enrollDrop_lname.setText(""); enrollDrop_dob.setValue(null);
    }
    @FXML
    void enroll(ActionEvent event) {
        if(enrollDrop_fname.getText().trim().equals("") || enrollDrop_lname.getText().trim().equals("")
            || enrollDrop_dob.getValue()==null || enrollDrop_credits.getText().trim().equals("")){
            messageArea.appendText("Please fill out all text fields\n");
        } else if(!isInt(enrollDrop_credits.getText())) {
            messageArea.appendText("Credits must be an integer\n");
        } else {
            enrollMethod();
        }
    }

    private void enrollDrop_dropMethod(){
        String fname = enrollDrop_fname.getText();
        String lname = enrollDrop_lname.getText();
        String date = enrollDrop_dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date dob = new Date(date);
        Profile profile = new Profile(lname,fname,dob);
        EnrollStudent pointer = new EnrollStudent(profile,0);

        if(enrollment.contains(pointer)){
            enrollment.remove(pointer);
            messageArea.appendText(profile.toString() + " dropped from enrollment\n");
        } else {
            messageArea.appendText(profile.toString() + " not enrolled\n");
        }

        enrollDrop_credits.setText(""); enrollDrop_fname.setText(""); enrollDrop_lname.setText(""); enrollDrop_dob.setValue(null);

    }
    @FXML
    void enrollDrop_drop(ActionEvent event) {

        if(enrollDrop_fname.getText().trim().equals("") || enrollDrop_lname.getText().trim().equals("")
            || enrollDrop_dob.getValue()==null){
            messageArea.appendText("Please fill out first name, last name, and date of birth\n");
        } else {
            enrollDrop_dropMethod();
        }

    }

    private boolean addResidentNoPrint(String line[]){

        if(line.length < 6) {
            System.out.println("Missing data in line command.");
            return false;
        }

        if(!isValidToAdd(line)) return false;
        Major[] majors = Major.values();
        Major pointer = Major.CS;
        int creditComp = Integer.parseInt(line[5]);
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        Resident resident = new Resident(profile,majors[pointer.getMajorIndex(line[4])],creditComp,0);
        if(!roster.contains(resident)){
            roster.add(resident);
            return true;
        } else {
            return false;
        }
    }

    private boolean addNonResidentNoPrint(String line[]){

        if(line.length < 6) {
            System.out.println("Missing data in line command.");
            return false;
        }

        if(!isValidToAdd(line)) return false;

        Major pointer = Major.CS;
        Major[] majors = Major.values();
        int creditComp = Integer.parseInt(line[5]);
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        NonResident nonResident = new NonResident(profile,majors[pointer.getMajorIndex(line[4])],creditComp);
        if(!roster.contains(nonResident)){
            roster.add(nonResident);
            return true;
        } else {
            return false;
        }
    }

    private boolean addTriStateNoPrint(String line[]) {

        if(line.length < 7) {
            System.out.println("Missing data in line command.");
            return false;
        }
        if(!isValidToAdd(line)) return false;
        if(!(line[6].equalsIgnoreCase("NY") || line[6].equalsIgnoreCase("CT"))) {
            System.out.println(line[6] + ": Invalid state code.");
            return false;
        }
        Major[] majors = Major.values();
        Major pointer = Major.CS;
        int creditComp = Integer.parseInt(line[5]);
        String state = line[6];
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        TriState triState = new TriState(profile,majors[pointer.getMajorIndex(line[4])],creditComp,state);
        if(!roster.contains(triState)) {
            roster.add(triState);
            return true;
        } else {
            return false;
        }
    }

    private boolean addInternationalNoPrint(String line[]){

        if(line.length < 6) {
            System.out.println("Missing data in line command.");
            return false;
        }
        if(!isValidToAdd(line)) return false;
        boolean isStudyAbroad = false;
        if(line.length == 6){
            isStudyAbroad = false;
        } else {
            if(line[6].equalsIgnoreCase("false")) isStudyAbroad = false;
            if(line[6].equalsIgnoreCase("true")) isStudyAbroad = true;
        }
        Major[] majors = Major.values();
        Major pointer = Major.CS;
        int creditComp = Integer.parseInt(line[5]);
        Date date = new Date(line[3]);
        Profile profile = new Profile(line[2],line[1],date);
        International international = new International(profile,majors[pointer.getMajorIndex(line[4])],creditComp,isStudyAbroad);
        if(!roster.contains(international)){
            roster.add(international);
            return true;
        } else {
            return false;
        }
    }

    @FXML
    boolean externalFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(null); // you could pass a stage reference here if you wanted.
        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                String command;
                String lines[];
                while(scanner.hasNextLine()) {
                    command = scanner.nextLine();
                    lines = command.split(",");
                    if (lines[0].equals("R")) {
                        addResidentNoPrint(lines);
                    } else if (lines[0].equals("N")) {
                        addNonResidentNoPrint(lines);
                    } else if (lines[0].equals("T")) {
                        addTriStateNoPrint(lines);
                    } else if (lines[0].equals("I")) {
                        addInternationalNoPrint(lines);
                    }
                }
            } catch (FileNotFoundException ex) {
                messageArea.appendText("File not found\n");
                return false;
            }
        }
        messageArea.appendText("Students loaded to the roster.\n"); return true;
    }



    @FXML
    void internationalButtonPress(ActionEvent event) {

        isStudyAbroad.setDisable(false);
        rb_ct.setDisable(true);
        rb_ny.setDisable(true);

        rb_ct.setSelected(false);
        rb_ny.setSelected(false);
        rb_nonResident.setSelected(false);

    }

    @FXML
    void nonResidentButtonPress(ActionEvent event) {

        rb_international.setDisable(false);
        rb_triState.setDisable(false);
        rb_ct.setDisable(true);
        rb_ny.setDisable(true);
        isStudyAbroad.setDisable(true);


        rb_international.setSelected(false);
        rb_triState.setSelected(false);
        rb_ct.setSelected(false);
        rb_ny.setSelected(false);
        isStudyAbroad.setSelected(false);

    }

    @FXML
    void printByProfile(ActionEvent event) {

        roster.print(messageArea);

    }

    @FXML
    void printBySchoolMajor(ActionEvent event) {

        roster.printBySchoolMajor(messageArea);

    }

    @FXML
    void printByStanding(ActionEvent event) {

        roster.printByStanding(messageArea);

    }

    @FXML
    void printEnrolledStudents(ActionEvent event) {

        enrollment.print(messageArea);

    }

    @FXML
    void printRBS(ActionEvent event) {

        roster.printSchool("RBS",messageArea);

    }

    @FXML
    void printSAS(ActionEvent event) {

        roster.printSchool("SAS",messageArea);

    }

    @FXML
    void printSCI(ActionEvent event) {

        roster.printSchool("SC&I",messageArea);

    }

    @FXML
    void printSOE(ActionEvent event) {

        roster.printSchool("SOE",messageArea);

    }

    @FXML
    void printSemesterEnd(ActionEvent event) {

        roster.printCompleted(enrollment,messageArea);

        semesterEnd.setDisable(true);

    }

    @FXML
    void printTuitionDue(ActionEvent event) {

        enrollment.printTuitionDue(roster,messageArea);

    }

    @FXML
    void residentButtonPress(ActionEvent event) {

        rb_triState.setDisable(true);
        rb_international.setDisable(true);
        rb_ct.setDisable(true);
        rb_ny.setDisable(true);
        isStudyAbroad.setDisable(true);

        rb_triState.setSelected(false);
        rb_international.setSelected(false);
        rb_ct.setSelected(false);
        rb_ny.setSelected(false);
        isStudyAbroad.setSelected(false);
    }

    private void roster_dropMethod(){
        String fname = roster_fname.getText();
        String lname = roster_lname.getText();
        String date = roster_dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date dob = new Date(date);
        Profile profile = new Profile(lname,fname,dob);
        Resident pointer = new Resident(profile, Major.CS, 0,0);
        EnrollStudent enrollStudentPointer = new EnrollStudent(profile,0);
        if(enrollment.contains(enrollStudentPointer)) enrollment.remove(enrollStudentPointer);
        if(roster.remove(pointer)) {
            messageArea.appendText(profile.toString() + " removed from the roster\n");
        } else {
            messageArea.appendText(profile.toString() + " not in the roster\n");
        }

        roster_fname.setText("");
        roster_lname.setText("");
        roster_creditCompleted.setText("");
        roster_dob.setValue(null);
    }

    @FXML
    void roster_drop(ActionEvent event) {
        if(roster_fname.getText().trim().equals("") || roster_lname.getText().trim().equals("")
                || roster_dob.getValue()==null){
            messageArea.appendText("Please fill out all text fields\n");
        } else if (roster_fname.getText().contains(" ") || roster_lname.getText().contains(" ")) {
            messageArea.appendText("First or last names cannot contains spaces\n");
        } else {
            roster_dropMethod();
        }
    }

    @FXML
    void triStateButtonPress(ActionEvent event) {

        rb_ct.setDisable(false);
        rb_ny.setDisable(false);
        isStudyAbroad.setDisable(true);

        isStudyAbroad.setSelected(false);
        rb_nonResident.setSelected(false);

        rb_ny.setSelected(true);

    }

    private boolean updateScholarshipMethod(){
        String fname = scholarship_fname.getText(); String lname = scholarship_lname.getText();
        int scholarship = Integer.parseInt(scholarship_amount.getText());
        if(scholarship > 10000 || scholarship < 1){
            messageArea.appendText(scholarship + ": invalid amount\n"); return false; }
        Date date = new Date(scholarship_dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        Profile profile = new Profile(lname,fname,date);
        NonResident finder = new NonResident(profile, Major.CS, 20);
        if(!roster.contains(finder)){
            messageArea.appendText(profile.toString() + " is not in the roster.\n"); return false; }
        Student student = roster.getStudent(finder);
        EnrollStudent ptr = new EnrollStudent(profile,0);
        EnrollStudent enrollStudent;
        if(enrollment.contains(ptr)) {
            enrollStudent = enrollment.getEnrollStudent(ptr); } else {
            messageArea.appendText(profile.toString() + " not enrolled\n");
            return false; }
        if((!(student instanceof Resident)) || enrollStudent.getCreditsEnrolled() < 12){
            messageArea.appendText(profile.toString() + " " + student.printType() + " is not eligible for the scholarship.\n");
            return false; }
        ((Resident) student).setScholarship(scholarship);
        messageArea.appendText(profile.toString() + ": scholarship amount updated.\n");
        scholarship_amount.setText(""); scholarship_dob.setValue(null); scholarship_fname.setText(""); scholarship_lname.setText("");
        return true;
    }

    @FXML
    void updateScholarship(ActionEvent event) {
        if(scholarship_fname.getText().trim().equals("") || scholarship_lname.getText().trim().equals("")
             || scholarship_dob.getValue()==null || scholarship_amount.getText().trim().equals("")) {
            messageArea.appendText("Please fill out all text fields\n");
        } else if(!isInt(scholarship_amount.getText())) {
            messageArea.appendText("Scholarship amount must be an integer\n");
        } else {
            updateScholarshipMethod();
        }
    }
}
