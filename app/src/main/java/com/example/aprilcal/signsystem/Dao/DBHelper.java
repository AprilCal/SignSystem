package com.example.aprilcal.signsystem.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aprilcal.signsystem.Activity.MainActivity;

import java.sql.PreparedStatement;

/**
 * Created by AprilCal on 2018/4/4.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String CREATE_USER_INFO = "create table user_info ("
            +"id integer primary key autoincrement,"
            +"user_name text unique not null,"
            +"user_password text not null)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
