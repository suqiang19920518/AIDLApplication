package com.example.server;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.server.bean.User;
import com.example.server.callback.LoginCallback;

public class LoginApiImpl extends ILoginApi.Stub {

    private Context context;

    public LoginApiImpl(Context context) {
        this.context = context;
    }

    @Override
    public void login(String name, String password) throws RemoteException {
        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "同步登录开始", Toast.LENGTH_LONG).show();
            }
        });
        try {
            //模拟阻塞
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginWithCallback(String name, String password, LoginCallback callback) throws RemoteException {
        try {
            //模拟阻塞
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (callback != null) {
            callback.onResult(true);
        }

    }

    @Override
    public void modifyNames(String[] nams) throws RemoteException {
        Log.e("收到的客户端对象", nams[1]);
        nams[1] = "6666";
    }

    @Override
    public User loginTypeIn(User user) throws RemoteException {
        Log.e("收到的客户端对象", user.toString());  // User{name='小王', password='123321'}
        user.setName("小强");
        user.setPassword("1qaz2wsx");
        return user;
    }

    @Override
    public User loginTypeOut(User user) throws RemoteException {
        Log.e("收到的客户端对象", user.toString());  // User{name='null', password='null'}
        user.setName("小强");
        user.setPassword("1qaz2wsx");
        return user;
    }

    @Override
    public User loginTypeInout(User user) throws RemoteException {
        Log.e("收到的客户端对象", user.toString());  // User{name='小王', password='123321'}
        user.setName("小强");
        user.setPassword("1qaz2wsx");
        return user;
    }
}
