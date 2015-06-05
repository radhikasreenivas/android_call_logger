package com.example.lenovo.phone;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

import java.util.logging.Handler;

public class MainActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
            SharedPreferences settings=getSharedPreferences("MYPREF", 0);
            int login;
            login=settings.getInt("logged",0);
            if(login==0) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                setTitle("Login");
                Button button = (Button) findViewById(R.id.button);
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        String phone_number;
                        EditText editText = (EditText) findViewById(R.id.editText);
                        phone_number = editText.getText().toString();
                        if (phone_number.equals("")) {
                                Toast.makeText(getApplicationContext(),
                                        "Enter valid number",
                                        Toast.LENGTH_LONG).show();
                                        return;
                            }
                        if(phone_number.length()!=10)
                        {
                                Toast.makeText(getApplicationContext(),
                                        "Enter valid number",
                                        Toast.LENGTH_LONG).show();
                                return;
                        }
                            SharedPreferences settings = getSharedPreferences("MYPREF", 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("my_number", phone_number);
                            editor.putInt("logged", 1);
                            editor.commit();
                            Intent k = new Intent(MainActivity.this, MainActivity2Activity.class);
                            startActivity(k);
                            MainActivity.this.finish();
                    }
                });
            }
            else
            {
                super.onCreate(savedInstanceState);
                Intent k = new Intent(MainActivity.this, MainActivity2Activity.class);
                startActivity(k);
                MainActivity.this.finish();
            }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
