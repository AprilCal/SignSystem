package com.example.aprilcal.signsystem.vo;

/**
 * Created by AprilCal on 2018/4/21.
 */

public class SignIn {

    private int signID;
    private int signInID;
    private int studentID;
    private String schoolID;
    private String studentName;
    private int signFlag;
    private int backup;
    private int deleted;

    public SignIn() { }

    public SignIn(int signID, int studentID, String schoolID, String studentName, int signFlag, int backup, int deleted) {
        this.signID = signID;
        this.studentID = studentID;
        this.schoolID = schoolID;
        this.studentName = studentName;
        this.signFlag = signFlag;
        this.backup = backup;
        this.deleted = deleted;
    }

    public SignIn(int signID, int signInID, int studentID, String schoolID, String studentName, int signFlag, int backup, int deleted) {
        this.signID = signID;
        this.signInID = signInID;
        this.studentID = studentID;
        this.schoolID = schoolID;
        this.studentName = studentName;
        this.signFlag = signFlag;
        this.backup = backup;
        this.deleted = deleted;
    }

    public int getBackup() {
        return backup;
    }

    public void setBackup(int backup) {
        this.backup = backup;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getSignID() {
        return signID;
    }

    public void setSignID(int signID) {
        this.signID = signID;
    }

    public int getSignInID() {
        return signInID;
    }

    public void setSignInID(int signInID) {
        this.signInID = signInID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(int signFlag) {
        this.signFlag = signFlag;
    }
}
