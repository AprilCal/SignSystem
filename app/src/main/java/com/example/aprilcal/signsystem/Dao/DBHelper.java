package com.example.aprilcal.signsystem.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by AprilCal on 2018/4/4.
 */

class DBHelper extends SQLiteOpenHelper {

    /**
     * Course table;
     */
    private static final String CREATE_COURSE_TABLE = "create table course_table ("
            +"course_id integer primary key autoincrement,"
            +"course_name text not null,"
            +"teacher_id integer not null,"
            +"course_create_date integer not null,"
            +"course_total_number integer not null,"
            +"backup integer not null,"
            +"deleted integer not null)";

    /**
     * Sign table;
     */
    private static final String CREATE_SIGN_TABLE = "create table sign_table ("
            +"sign_id integer primary key autoincrement,"
            +"course_id integer not null,"
            +"teacher_id integer not null,"
            +"sign_date integer not null,"
            +"sign_total_number integer not null,"
            +"sign_actual_number integer not null,"
            +"backup integer not null,"
            +"deleted integer not null)";

    /**
     * Sign in table;
     */
    private static final String CREATE_SIGN_IN_TABLE = "create table sign_in_table ("
            +"sign_in_id integer primary key autoincrement,"
            +"sign_id integer not null,"
            +"student_id integer not null,"
            +"school_id text not null,"
            +"student_name text not null,"
            +"sign_flag integer not null,"
            +"backup integer not null,"
            +"deleted integer not null)";

    /**
     * Elective table;
     */
    private final static String CREATE_ELECTIVE_TABLE = "create table elective_table ("
            +"student_id integer,"
            +"school_id text,"
            +"course_id integer,"
            +"student_name text not null,"
            +"backup integer not null,"
            +"deleted integer not null,"
            +"primary key (school_id,course_id))";

    private static final String IF_COURSE_TABLE_EXIST = "";
    private static final String IF_DATABASE_EXIST = "";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //TODO remove log info;
        Log.d("msg:","Creating database!!!");
        db.execSQL(CREATE_COURSE_TABLE);
        db.execSQL(CREATE_SIGN_TABLE);
        db.execSQL(CREATE_SIGN_IN_TABLE);
        db.execSQL(CREATE_ELECTIVE_TABLE);
        Log.d("msg:","create success!!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}