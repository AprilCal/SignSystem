package com.example.aprilcal.signsystem.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.aprilcal.signsystem.vo.Elective;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/21.
 */

public class ElectiveDao {
    private static String DBName = "signDB";
    private static String tableName = "elective_table";

    /**
     * @param context
     * @param elective
     * @return the row ID of the newly inserted row, or -1 if an error occurred;
     */
    public static long insert(Context context, Elective elective){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_id",elective.getStudentID());
        values.put("school_id",elective.getSchoolID());
        values.put("course_id", elective.getCourseID());
        values.put("student_name",elective.getStudentName());
        values.put("backup",0);
        values.put("deleted",0);
        return db.insert(tableName,null,values);
    }

    public static void insert(Context context, List<Elective> electiveList){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(Elective elective : electiveList){
            values.put("student_id",elective.getStudentID());
            values.put("school_id",elective.getSchoolID());
            values.put("course_id", elective.getCourseID());
            values.put("student_name",elective.getStudentName());
            values.put("backup",0);
            values.put("deleted",0);
            db.insert(tableName,null,values);
        }
    }

    public static void deleteAllByCourseID(Context context, int courseID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName, "course_id = ?", new String[]{ String.valueOf(courseID)});
    }

    public static void deleteByCourseIDAndSchoolID(Context context, String schoolID, int courseID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName, "course_id = ? and school_id = ?", new String[]{ String.valueOf(courseID),schoolID});
    }

    public static void physicallyDelete(Context context, String schoolID, int courseID){

    }

    public static Elective selectElectiveBySchoolID(Context context, String schoolID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(tableName,null,"school_id = ? and deleted = ?", new String[]{ schoolID, "0"}, null, null, null);
        int studentID;
        int courseID;
        String studentName;
        int backup;
        while(cursor.moveToNext()){
            studentID = cursor.getInt(cursor.getColumnIndex("student_id"));
            courseID = cursor.getInt(cursor.getColumnIndex("course_id"));
            studentName = cursor.getString(cursor.getColumnIndex("student_name"));
            backup = cursor.getInt(cursor.getColumnIndex("backup"));
            return new Elective(studentID,schoolID,courseID,studentName,backup,0);
        }
        return null;
    }

    /**
     * TODO reutrn null or empty list when cursor is empty;
     * @param context
     * @param courseID
     * @return list of student of specific course;
     */
    public static List<Elective> selectAllElectiveByCourseID(Context context, int courseID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Elective> electiveList = new ArrayList<Elective>();

        Cursor cursor = db.query(tableName,null,"deleted = ? and course_id = ?",new String[] { "0" , String.valueOf(courseID) },null,null,null);
        int studentID;
        String schoolID;
        String studentName;
        int backup;
        int deleted;

        while (cursor.moveToNext())
        {
            schoolID = cursor.getString(cursor.getColumnIndex("school_id"));
            studentID = cursor.getInt(cursor.getColumnIndex("student_id"));
            studentName = cursor.getString(cursor.getColumnIndex("student_name"));
            backup = cursor.getInt(cursor.getColumnIndex("backup"));
            deleted = cursor.getInt(cursor.getColumnIndex("deleted"));
            electiveList.add(new Elective(studentID,schoolID,courseID,studentName,backup,deleted));
        }
        cursor.close();
        return electiveList;
    }
}
