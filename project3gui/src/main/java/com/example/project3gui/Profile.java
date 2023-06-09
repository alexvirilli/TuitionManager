package com.example.project3gui;


public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob;

    public Profile(String lname, String fname, Date dob){
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }
    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
    public Date getDob(){
        return dob;
    }
    @Override
    public String toString() {
       return this.fname + " " + this.lname + " " + this.dob.toString();
    }
    @Override
    public boolean equals(Object profile){
        if(this == profile) return true;
        if(!(profile instanceof Profile)) return false;
        Profile p = (Profile) profile;
        if(this.fname.equalsIgnoreCase(p.getFname()) && this.lname.equalsIgnoreCase(p.getLname()) && this.dob.equals(p.getDob())) return true;
        return false;
    }
    @Override
    public int compareTo(Profile p){
        if(this.lname.compareTo(p.getLname()) < 0) return -1;
        if(this.lname.compareTo(p.getLname()) > 0) return 1;
        if(this.fname.compareTo(p.getFname()) < 0) return -1;
        if(this.fname.compareTo(p.getFname()) > 0) return 1;
        if(this.dob.compareTo(p.getDob()) < 0) return -1;
        if(this.dob.compareTo(p.getDob()) > 0) return 1;
        return 0;
    }
}
