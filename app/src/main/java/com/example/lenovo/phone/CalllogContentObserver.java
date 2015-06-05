package com.example.lenovo.phone;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by LENOVO on 6/1/2015.
 */
public class CalllogContentObserver extends ContentObserver {

    Context context;
    Handler handler;

    public CalllogContentObserver(Context context,Handler handler) {
        super(handler);
        this.context=context;
        // this.handler=handler;
        Log.d("self_change", "MyContentObserver.constructor ");
    }

    public boolean deliverSelfNotifications() {
        return true;
    }

    @Override
    public void onChange(boolean selfChange) {

        this.onChange(selfChange);
        //   MainActivity2Activity getcalls_obj=new MainActivity2Activity();
        //  getcalls_obj.getCallDetails();
        // Toast.makeText(getcalls_obj,"on change",Toast.LENGTH_LONG).show();
        Log.d("self_change", "MyContentObserver.onChange(" + selfChange + ")");
        // here you call the method to fill the list
    }


}
