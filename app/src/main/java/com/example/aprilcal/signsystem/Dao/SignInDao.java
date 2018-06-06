package com.example.aprilcal.signsystem.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.aprilcal.signsystem.vo.SignIn;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/21.
 */

public class SignInDao {
    //TODO modify all Dao, remove DBName in to configuration file;
    public final static String DBName = "signDB";
    public final static String tableName = "sign_in_table";

    //TODO return long or void ?
    public static void insert(Context context, SignIn signIn){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sign_id",signIn.getSignID());
        values.put("student_id",signIn.getStudentID());
        values.put("school_id",signIn.getSchoolID());
        values.put("student_name",signIn.getStudentName());
        values.put("sign_flag",signIn.getSignFlag());
        values.put("backup",0);
        values.put("deleted",0);
        db.insert("sign_in_table",null,values);
    }

    //TODO return long or void ?
    public static void insert(Context context, List<SignIn> signInList){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(SignIn signIn : signInList){
            values.put("sign_id",signIn.getSignID());
            values.put("student_id",signIn.getStudentID());
            values.put("school_id",signIn.getSchoolID());
            values.put("student_name",signIn.getStudentName());
            values.put("sign_flag",signIn.getSignFlag());
            values.put("backup",0);
            values.put("deleted",0);
            long i = db.insert("sign_in_table",null,values);
            Log.d("insert id",String.valueOf(i));
        }
    }

    public static void deleteAllBySignID(Context context, int signID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName, "sign_id = ?", new String[]{ String.valueOf(signID)});
    }

    public static List<SignIn> selectAllPresentBySignID(Context context, int signID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<SignIn> signInList = new ArrayList<SignIn>();

        Cursor cursor = db.query(tableName,null,"deleted = ? and sign_id = ? and sign_flag = ?",
                new String[] { "0" , String.valueOf(signID) , "1" },null,null,null);

        int signInID;
        int studentID;
        String schoolID;
        String studentName;
        int signFlag;
        int backup;
        int deleted = 0;
        while (cursor.moveToNext())
        {
            signInID = cursor.getInt(cursor.getColumnIndex("sign_in_id"));
            studentID = cursor.getInt(cursor.getColumnIndex("sign_id"));
            schoolID = cursor.getString(cursor.getColumnIndex("school_id"));
            studentName = cursor.getString(cursor.getColumnIndex("student_name"));
            signFlag = cursor.getInt(cursor.getColumnIndex("sign_flag"));
            backup = cursor.getInt(cursor.getColumnIndex("backup"));
            signInList.add(new SignIn(signID,signInID,studentID,schoolID,studentName,signFlag,backup,deleted));
        }
        cursor.close();
        return signInList;
    }

    public static List<SignIn> selectAllAbsentBySignID(Context context, int signID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<SignIn> signInList = new ArrayList<SignIn>();

        Cursor cursor = db.query(tableName,null,"deleted = ? and sign_id = ? and sign_flag = ?",
                new String[] { "0" , String.valueOf(signID) , "0" },null,null,null);

        int signInID;
        int studentID;
        String schoolID;
        String studentName;
        int signFlag;
        int backup;
        int deleted = 0;
        while (cursor.moveToNext())
        {
            signInID = cursor.getInt(cursor.getColumnIndex("sign_in_id"));
            studentID = cursor.getInt(cursor.getColumnIndex("sign_id"));
            schoolID = cursor.getString(cursor.getColumnIndex("school_id"));
            studentName = cursor.getString(cursor.getColumnIndex("student_name"));
            signFlag = cursor.getInt(cursor.getColumnIndex("sign_flag"));
            backup = cursor.getInt(cursor.getColumnIndex("backup"));
            signInList.add(new SignIn(signID,signInID,studentID,schoolID,studentName,signFlag,backup,deleted));
        }
        cursor.close();
        return signInList;
    }

    public static List<SignIn> selectAllSignInBySignID(Context context, int signID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<SignIn> signInList = new ArrayList<SignIn>();

        Cursor cursor = db.query(tableName,null,"deleted = ? and sign_id = ?",new String[] { "0" , String.valueOf(signID) },null,null,null);

        int signInID;
        int studentID;
        String schoolID;
        String studentName;
        int signFlag;
        int backup;
        int deleted = 0;
        while (cursor.moveToNext())
        {
            signInID = cursor.getInt(cursor.getColumnIndex("sign_in_id"));
            studentID = cursor.getInt(cursor.getColumnIndex("sign_id"));
            schoolID = cursor.getString(cursor.getColumnIndex("school_id"));
            studentName = cursor.getString(cursor.getColumnIndex("student_name"));
            signFlag = cursor.getInt(cursor.getColumnIndex("sign_flag"));
            backup = cursor.getInt(cursor.getColumnIndex("backup"));
            signInList.add(new SignIn(signID,signInID,studentID,schoolID,studentName,signFlag,backup,deleted));
        }
        cursor.close();
        return signInList;
    }
}
