package com.example.deepakrattan.localboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by Deepak Rattan on 23-Jan-17.
 */

public class BoundService extends Service {

    private final IBinder myBinder =new MyLocalBinder();

    //Constructor
    public BoundService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Bound Service", "onBind called");
        return myBinder;
    }

    //Local bound service communicate with bound clients by passing Binder object to the client
    //This is achieved by creating a Binder subclass and add some methods that can be called by the client
    public class MyLocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }

    }


    public String getData(){
    String data = "Data from Local Private Service";
        return data;
    }

}
