package com.example.aprilcal.signsystem.vo;

/**
 * Created by AprilCal on 2018/4/7.
 */

public class Sign {
    private int signID;
    private int courseID;
    private int teacherID;
    private int signDate;
    private int totalNumber;
    private int actualNumber;

    public Sign(){

    }

    public Sign(int signID, int courseID, int teacherID, int signDate, int totalNumber, int actualNumber) {
        this.signID = signID;
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.signDate = signDate;
        this.totalNumber = totalNumber;
        this.actualNumber = actualNumber;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getSignID() {
        return signID;
    }

    public void setSignID(int signID) {
        this.signID = signID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getSignDate() {
        return signDate;
    }

    public void setSignDate(int signDate) {
        this.signDate = signDate;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(int actualNumber) {
        this.actualNumber = actualNumber;
    }
}
