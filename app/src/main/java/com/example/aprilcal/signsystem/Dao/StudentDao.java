package com.example.aprilcal.signsystem.Dao;

import android.content.Context;

import com.example.aprilcal.signsystem.vo.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/21.
 */

public class StudentDao {

    //TODO implement these method;

    public static List<Student> selectAllStudentByCourseID(Context context,int courseID){
        List<Student> studentList = new ArrayList<Student>();
        return studentList;
    }

    public static boolean insert(Context context, Student student){
        return true;
    }

    public static List<Student> selectAllAbsentStudentBySignID(Context context, int courseID){
        List<Student> absentStudentList = new ArrayList<Student>();
        return absentStudentList;
    }
}
