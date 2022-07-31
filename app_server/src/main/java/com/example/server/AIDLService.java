package com.example.server;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class AIDLService extends Service {

    private IBinder iBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder = new LoginApiImpl(getApplicationContext());
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        iBinder = null;
    }

}
