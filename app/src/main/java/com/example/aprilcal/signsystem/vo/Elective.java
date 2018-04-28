package com.example.aprilcal.signsystem.vo;

/**
 * Created by jhon on 2018/4/21.
 */

public class Elective {
    private int studentID;
    private String schoolID;
    private int courseID;
    private String studentName;
    private int backup;
    private int deleted;

    public Elective() {
    }

    public Elective(int studentID, String schoolID, int courseID, String studentName, int backup, int deleted) {
        this.studentID = studentID;
        this.schoolID = schoolID;
        this.courseID = courseID;
        this.studentName = studentName;
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

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
