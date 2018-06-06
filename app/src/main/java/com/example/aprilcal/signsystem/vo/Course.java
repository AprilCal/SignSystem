package com.example.aprilcal.signsystem.vo;

/**
 * Created by AprilCal on 2018/4/7.
 */

public class Course {
    private int courseID;
    private int teacherID;
    private String courseName;
    private int createDate;
    private int totalNumber;
    private int deleted;
    private int backup;

    public Course(){

    }

    public Course(int courseID, int teacherID, String courseName, int createDate, int totalNumber, int backup, int deleted) {
        this.courseID = courseID;
        this.teacherID = teacherID;
        this.courseName = courseName;
        this.createDate = createDate;
        this.totalNumber = totalNumber;
        this.backup = backup;
        this.deleted = deleted;
    }
    public int isDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int isBackup() {
        return backup;
    }

    public void setBackup(int backup) {
        this.backup = backup;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
}
