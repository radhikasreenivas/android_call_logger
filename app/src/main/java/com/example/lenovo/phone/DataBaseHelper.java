package com.example.lenovo.phone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LENOVO on 5/25/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "call_logdb";
    private static final int DATABASE_VERSION = 4;
    private static DataBaseHelper instance;


    public static final  String id ="id";
    public static final String phone_number="phone_number";
    public static final  String call_type="call_type";
    public static  final String call_date="call_date";
    public static  final String  call_duration="call_duration";
    public static final String table_name="call_logtable";
    private static final String create_table = "create table if not exists "+table_name+" ( "+id +" integer primary key  , "+phone_number+" text, "+call_type+" text, "+call_date+" text, "+call_duration+" text);";


    DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
       // Log.d("Database helper","database created");
    }

    public void onCreate(SQLiteDatabase database) {

      database.execSQL(create_table);
       // Log.d("Database helper", "table created");
      //  database.execSQL("create table if not exists call_log ( id integer primary key auto increment, phone_number text , call_type text ,call_date text ,call_duration text ) ;");

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }
}
