package com.example.guyao.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //service与activity绑定成功
            mBinder = (MyService.DownloadBinder) service;
            mBinder.startDownload();
            mBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//service与activity解绑调用

        }
    };
    private Intent mIntent;
    private MyService.DownloadBinder mBinder;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntent = new Intent(this, MyService.class);
    }

    public void bind(View view) {
        bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        unbindService(mServiceConnection);
    }

    public void start(View view) {
        startService(mIntent);
    }

    public void stop(View view) {
        stopService(mIntent);
    }
}
