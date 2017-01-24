package com.example.deepakrattan.localboundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BoundService boundService;
    boolean isBound = false;
    private Button btnGetData;
    private TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewByID
        btnGetData = (Button) findViewById(R.id.btnGetData);
        txtData = (TextView) findViewById(R.id.txtData);

        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtData.setText(boundService.getData());
            }
        });
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyLocalBinder binder = (BoundService.MyLocalBinder) service;
            boundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
