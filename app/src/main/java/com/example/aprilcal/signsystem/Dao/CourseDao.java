package com.example.aprilcal.signsystem.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.aprilcal.signsystem.vo.Course;

/**
 * Created by jhon on 2018/4/7.
 */

public class CourseDao {
    public static boolean insert(Context context, Course course){
        DBHelper dbHelper = new DBHelper(context,"signDB",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("course_name",course.getCourseName());
        values.put("course_teacher_id",course.getTeacherID());
        values.put("course_create_date", String.valueOf(course.getCreateDate()));
        values.put("course_total_number",course.getTotalNumber());
        db.insert("course_table",null,values);
        return true;
    }
    public static boolean delete(Context context, int courseID){
        return true;
    }
}
