package com.example.lenovo.phone;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View;
import android.database.Cursor;
import android.provider.CallLog;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.LogRecord;

import android.widget.TextView;

public class MainActivity2Activity extends ActionBarActivity {

            private db dbconnection;
    @Override
     public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        setTitle("Call Log");
        SharedPreferences settings=getSharedPreferences("MYPREF", 0);
        TextView textView = (TextView)findViewById(R.id.textView2);
        textView.setText("User number:"+settings.getString("my_number",""));
        dbconnection=new db(this);
        dbconnection.open();
       getCallDetails();
        Handler handler=new Handler();
        Context context=getApplicationContext();
        //this.getApplicationContext().getContentResolver().registerContentObserver(android.provider.CallLog.Calls.CONTENT_URI, true, new CalllogContentObserver(context,handler));
       // this.getApplicationContext().getContentResolver().registerContentObserver(Uri.parse("content //call_log/calls"), true, new CalllogContentObserver(context,handler));

        //this.getContentResolver().unregisterContentObserver(new CalllogContentObserver(context,handler));
        StringBuffer sb =new StringBuffer();
        sb.append(dbconnection.readfrom_db());
        textView = (TextView)findViewById(R.id.textview_call);
        textView.setText(sb);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                StringBuffer sb = new StringBuffer();
                sb.append(dbconnection.readfrom_db());
                TextView textView = (TextView) findViewById(R.id.textview_call);
                textView.setText(sb);

            }
        });
        ImageButton button3 = (ImageButton)findViewById(R.id.imageButton);
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                StringBuffer sb =new StringBuffer();
                sb.append(dbconnection.readSpecificfrom_db("Incoming"));
                TextView textView = (TextView)findViewById(R.id.textview_call);
                textView.setText(sb);
            }
        });
        ImageButton button4 = (ImageButton)findViewById(R.id.imageButton2);
        button4.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                StringBuffer sb =new StringBuffer();
                sb.append(dbconnection.readSpecificfrom_db("Outgoing"));
                TextView textView = (TextView)findViewById(R.id.textview_call);
                textView.setText(sb);
            }
        });
        ImageButton button5= (ImageButton)findViewById(R.id.imageButton3);
        button5.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                StringBuffer sb =new StringBuffer();
                sb.append(dbconnection.readSpecificfrom_db("Missed"));
                TextView textView = (TextView)findViewById(R.id.textview_call);
                textView.setText(sb);
            }
        });
        Button button6= (Button)findViewById(R.id.button6);
        button6.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                SharedPreferences settings = getSharedPreferences("MYPREF", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("logged", 0);
                editor.commit();
                Intent k = new Intent(MainActivity2Activity.this, MainActivity.class);
                startActivity(k);
                MainActivity2Activity.this.finish();
            }
        });
    }


    public  void getCallDetails() {

        String strOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, strOrder);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        dbconnection.delete();
        int id=0;
        while (managedCursor.moveToNext()) {
            String phNum = managedCursor.getString(number);
            String callTypeCode = managedCursor.getString(type);
            String strcallDate = managedCursor.getString(date);
            Date callDate = new Date(Long.valueOf(strcallDate));
            String callDuration = managedCursor.getString(duration);
            String callType = null;
            int callcode = Integer.parseInt(callTypeCode);
            switch (callcode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    callType = "Outgoing";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    callType = "Incoming";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    callType = "Missed";
                    break;
            }
            String call_date=null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try{
                call_date=format.format(callDate);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            id=id+1;
           dbconnection.insertdb(id,phNum, callType,call_date,callDuration);

        }
        managedCursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
