package com.example.aprilcal.signsystem.vo;

/**
 * Created by AprilCal on 2018/4/7.
 */

public class Student {
    // system ID, allocated by system.
    private int ID;
    // school ID, filled by student.
    private String schoolID;
    private String studentName;
    private String school;
    private String phoneNumber;
    private String mailAddress;
    private String password;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudentID() {
        return schoolID;
    }

    public void setStudentID(String schoolID) {
        this.schoolID= schoolID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
