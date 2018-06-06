package com.example.aprilcal.signsystem.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.aprilcal.signsystem.vo.Sign;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AprilCal on 2018/4/9.
 */

public class SignDao {
    private static final String DBName = "signDB";
    private static final String tableName = "sign_table";

    public static int insert(Context context, Sign sign){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("course_id",sign.getCourseID());
        values.put("teacher_id",sign.getTeacherID());
        values.put("sign_date",sign.getSignDate());
        values.put("sign_total_number",sign.getTotalNumber());
        values.put("sign_actual_number",sign.getActualNumber());
        values.put("backup",0);
        values.put("deleted",0);
        db.insert("sign_table",null,values);

        String queryID = "select last_insert_rowid() from " + "sign_table";
        Cursor cursor = db.rawQuery(queryID, null);

        int ID = -1;
        if(cursor.moveToFirst()){
            ID = cursor.getInt(0);
        }
        return ID;
    }

    public static boolean updateActualNumber(Context context, int signID, int actualNumber){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sign_actual_number", actualNumber);
        if(db.update(tableName, values, "sign_id = ?", new String[] { String.valueOf(signID) })>0){
            return true;
        }
        return false;
    }

    public static void deleteBySignID(Context context,int signID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName, "sign_id = ?", new String[]{ String.valueOf(signID)});
    }

    //TODO refactor, return null when signList is empty;

    public static List<Sign> selectAllSignByCourseID(Context context,int courseID){
        DBHelper dbHelper = new DBHelper(context,DBName,null,1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List <Sign> signList = new ArrayList<Sign>();

        Cursor cursor = db.query("sign_table",null,"deleted = ? and course_id = ?", new String[] { "0" , String.valueOf(courseID) },null,null,null);
        int signID;
        int teacherID;
        long signDate;
        int totalNumber;
        int actualNumber;
        int backup;
        int deleted;

        while (cursor.moveToNext())
        {
            signID = cursor.getInt(cursor.getColumnIndex("sign_id"));
            teacherID = cursor.getInt(cursor.getColumnIndex("teacher_id"));
            signDate = cursor.getLong(cursor.getColumnIndex("sign_date"));
            totalNumber = cursor.getInt(cursor.getColumnIndex("sign_total_number"));
            actualNumber = cursor.getInt(cursor.getColumnIndex("sign_actual_number"));
            backup = cursor.getInt(cursor.getColumnIndex("backup"));
            deleted = cursor.getInt(cursor.getColumnIndex("deleted"));
            signList.add(new Sign(signID,courseID,teacherID,signDate,totalNumber,actualNumber,backup,deleted));
        }
        cursor.close();
        return signList;
    }
}
