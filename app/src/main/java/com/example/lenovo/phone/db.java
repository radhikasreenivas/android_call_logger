package com.example.lenovo.phone;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LENOVO on 5/25/2015.
 */
public class db {

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;


   public db(Context context) {
        this.mContext = context;
    }

    public db open() throws SQLException {
        dbHelper = new DataBaseHelper(mContext);
        return this;
    }


    public void close() {
        dbHelper.close();
    }

    public void insertdb( int id,String ph_num, String call_type, String calldate, String call_duration) {
        dbHelper = new DataBaseHelper(mContext);
       database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.id,id);
        values.put(DataBaseHelper.phone_number, ph_num);
        values.put(DataBaseHelper.call_type, call_type);
        values.put(DataBaseHelper.call_date, calldate);
        values.put(DataBaseHelper.call_duration, call_duration);
       database.insert(DataBaseHelper.table_name, null, values);
       // Log.d("Database helper", "values inserted");
        database.close();
    }

    StringBuffer readSpecificfrom_db(String type)
    {
        String ph_number=null;
        String call_type=null;
        String call_date=null;
        String call_duration=null;
        StringBuffer sb = new StringBuffer();
        //sb.append("Call Log :");
        Cursor cursor_object=getinformation();
        cursor_object.moveToFirst();
        do {
            if((cursor_object.getString(1)).equals(type)) {
                ph_number = cursor_object.getString(0);
                call_type = cursor_object.getString(1);
                call_date = cursor_object.getString(2);
                call_duration = cursor_object.getString(3);
                if(type=="Missed") {
                    sb.append("\nPhone Number:--- " + ph_number +
                             " \nCall Type:--- " + call_type +
                            " \nCall Date:--- " + call_date
                         //   + " \nCall duration in sec :--- " + call_duration
                         );
                    sb.append("\n----------------------------------");
                }
                else
                {
                    sb.append("\nPhone Number:--- " + ph_number +
                            " \nCall Type:--- " + call_type +
                            " \nCall Date:--- " + call_date
                            + " \nCall duration in sec :--- " + call_duration);
                    sb.append("\n----------------------------------");
                }
            }
        }while(cursor_object.moveToNext());

        return sb;

    }

    StringBuffer readfrom_db()
    {
        String ph_number=null;
        String call_type=null;
        String call_date=null;
        String call_duration=null;
        //  int id=0;
        StringBuffer sb = new StringBuffer();
       // sb.append("Call Log :");
        Cursor cursor_object=getinformation();
        cursor_object.moveToFirst();
        do {
            ph_number=cursor_object.getString(0);
            call_type=cursor_object.getString(1);
            call_date=cursor_object.getString(2);
            call_duration=cursor_object.getString(3);
            sb.append("\nPhone Number:--- " + ph_number +
                    " \nCall Type:--- " + call_type +
                    " \nCall Date:--- " + call_date
                    + " \nCall duration in sec :--- " + call_duration);
            sb.append("\n----------------------------------");

        }while(cursor_object.moveToNext());
        return sb;


    }


    public Cursor getinformation()
    {
        dbHelper = new DataBaseHelper(mContext);
        database = dbHelper.getReadableDatabase();
        String[] columns={DataBaseHelper.phone_number,DataBaseHelper.call_type,DataBaseHelper.call_date,DataBaseHelper.call_duration};
        Cursor cursor_object=database.query(DataBaseHelper.table_name,columns,null,null,null,null,null);
        return cursor_object;
    }

    public void delete()
    {
        dbHelper = new DataBaseHelper(mContext);
        database = dbHelper.getWritableDatabase();
       // String[] columns={DataBaseHelper.phone_number,DataBaseHelper.call_type,DataBaseHelper.call_date,DataBaseHelper.call_duration};
        database.delete(DataBaseHelper.table_name,null,null);
    }

}