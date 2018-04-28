package com.example.aprilcal.signsystem.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.aprilcal.signsystem.Activity.LinkInfo;
import com.example.aprilcal.signsystem.vo.Course;

import java.util.ArrayList;
import java.util.List;

import static com.example.aprilcal.signsystem.Activity.ClientMainActivity.linkInfos;

/**
 * Created by AprilCal on 2018/4/7.
 */

public class CourseDao {
    //TODO: DBName configuration
    private static String DBName = "signDB";
    private static String tableName = "course_table";

    /**
     * default value of backup & deleted is false;
     * @param context
     * @param course
     * @return
     */
    public static int insert(Context context, Course course){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("course_name",course.getCourseName());
        values.put("teacher_id",course.getTeacherID());
        values.put("course_create_date", String.valueOf(course.getCreateDate()));
        values.put("course_total_number",course.getTotalNumber());
        values.put("backup",0);
        values.put("deleted",0);
        db.insert("course_table",null,values);

        String queryID = "select last_insert_rowid() from " + tableName;
        Cursor cursor = db.rawQuery(queryID, null);

        int ID = -1;
        if(cursor.moveToFirst()){
            ID = cursor.getInt(0);
        }
        return ID;
    }

    /**
     * Do not delete an item physically.
     * Return true on success and false on failed;
     * @param context
     * @param courseID
     * @return
     */
    public static boolean delete(Context context, int courseID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("deleted", 1);
        if(db.update(tableName, values, "course_id = ?", new String[] { String.valueOf(courseID) })>0){
            return true;
        }
        return false;
    }

    /**
     * TODO implement;
     * Physically remove a course record by courseID;
     * @param context
     * @param courseID
     * @return
     */
    public static boolean physicallyDelete(Context context, int courseID){
        return true;
    }

    /**
     * TODO return null when list is empty;
     * return all courses where deleted is false.
     * @param context
     * @return
     */
    public static List<Course> selectAllCourse(Context context){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List <Course> courseList = new ArrayList<Course>();

        Cursor cursor = db.query("course_table",null,"deleted = ?",new String[] { "0" },null,null,null);
        int course_id;
        String course_name;
        int teacher_id;
        int course_create_date;
        int course_total_number;
        int backup;
        int deleted;

        while (cursor.moveToNext())
        {
            course_id = cursor.getInt(cursor.getColumnIndex("course_id"));
            course_name = cursor.getString(cursor.getColumnIndex("course_name"));
            teacher_id = cursor.getInt(cursor.getColumnIndex("teacher_id"));
            course_create_date = cursor.getInt(cursor.getColumnIndex("course_create_date"));
            course_total_number = cursor.getInt(cursor.getColumnIndex("course_total_number"));
            backup = cursor.getInt(cursor.getColumnIndex("backup"));
            deleted = cursor.getInt(cursor.getColumnIndex("deleted"));
            courseList.add(new Course(course_id,teacher_id,course_name,course_create_date,course_total_number,backup,deleted));
        }
        cursor.close();
        return courseList;
    }
}
