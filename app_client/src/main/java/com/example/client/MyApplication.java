package com.example.client;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.server.ILoginApi;

public class MyApplication extends Application {

    private boolean isConnected;
    private ILoginApi loginApi;
    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            loginApi = ILoginApi.Stub.asInterface(service);
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        bindService();
    }

    public void bindService() {
        Intent intent = new Intent();
        //绑定服务方法一
        intent.setPackage("com.example.server");
        intent.setAction("com.example.server.action");

        /*
        //绑定服务方法二
        intent.setComponent(new ComponentName("com.example.server","com.example.server.AIDLService"));
        //绑定服务方法三
        intent.setClassName("com.example.server","com.example.server.AIDLService");
         */
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if (isConnected) {
            unbindService(serviceConnection);
        }
    }

    /**
     * 获取服务端接口实现对象，即 LoginApiImpl
     *
     * @return
     */
    public ILoginApi getLoginApi() {
        return loginApi;
    }
}
