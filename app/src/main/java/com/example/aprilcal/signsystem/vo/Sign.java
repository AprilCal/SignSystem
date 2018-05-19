package com.example.aprilcal.signsystem.vo;

/**
 * Created by AprilCal on 2018/4/7.
 */

public class Sign {
    private int signID;
    private int courseID;
    private int teacherID;
    private long signDate;
    private int totalNumber;
    private int actualNumber;
    private int backup;
    private int deleted;

    public Sign(){

    }

    public Sign(int courseID, int teacherID, long signDate, int totalNumber, int actualNumber, int backup, int deleted) {
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.signDate = signDate;
        this.totalNumber = totalNumber;
        this.actualNumber = actualNumber;
        this.backup = backup;
        this.deleted = deleted;
    }

    public Sign(int signID, int courseID, int teacherID, long signDate, int totalNumber, int actualNumber, int backup, int deleted) {
        this.signID = signID;
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.signDate = signDate;
        this.totalNumber = totalNumber;
        this.actualNumber = actualNumber;
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

    public long getSignDate() {
        return signDate;
    }

    public void setSignDate(long signDate) {
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
