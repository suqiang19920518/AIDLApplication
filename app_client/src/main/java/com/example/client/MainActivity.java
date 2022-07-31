package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.server.ILoginApi;
import com.example.server.bean.User;
import com.example.server.callback.LoginCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ILoginApi loginApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_login_syn).setOnClickListener(this);
        findViewById(R.id.btn_login_asyn).setOnClickListener(this);
        findViewById(R.id.btn_modify_names).setOnClickListener(this);
        findViewById(R.id.btn_login_type_in).setOnClickListener(this);
        findViewById(R.id.btn_login_type_out).setOnClickListener(this);
        findViewById(R.id.btn_login_type_inout).setOnClickListener(this);
        loginApi = MyApplication.getInstance().getLoginApi();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login_syn) {  //同步登录
            try {
                if (loginApi != null) {
                    loginApi.login("小张", "123@@@");
                    Toast.makeText(MainActivity.this, "同步登录成功", Toast.LENGTH_LONG).show();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.btn_login_asyn) {  //异步登录
            try {
                if (loginApi != null) {
                    loginApi.loginWithCallback("小张", "123@@@", new LoginCallback.Stub() {
                        @Override
                        public void onResult(boolean isSuccess) throws RemoteException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "异步登录成功", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                    Toast.makeText(MainActivity.this, "远程异步调用，不阻塞", Toast.LENGTH_LONG).show();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.btn_modify_names) {
            if (loginApi != null) {
                String[] names = new String[]{"小张", "小明","小王"};
                try {
                    Log.e("客户端原对象", names[1]);   // User{name='小强', password='1qaz2wsx'}
                    loginApi.modifyNames(names);
                    Log.e("客户端对象", names[1]);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (id == R.id.btn_login_type_in) {
            User user = new User();
            user.setName("小王");
            user.setPassword("123321");
            Log.e("客户端原对象", user.toString());   // User{name='小王', password='123321'}
            try {
                if (loginApi != null) {
                    User loginWithUser = loginApi.loginTypeIn(user);
                    Log.e("客户端对象", user.toString());   // User{name='小王', password='123321'}
                    Log.e("服务端返回", loginWithUser.toString());  // User{name='小强', password='1qaz2wsx'}
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.btn_login_type_out) {
            User user = new User();
            user.setName("小王");
            user.setPassword("123321");
            Log.e("客户端原对象", user.toString());  // User{name='小王', password='123321'}
            try {
                if (loginApi != null) {
                    User loginWithUser = loginApi.loginTypeOut(user);
                    Log.e("客户端对象", user.toString());   // User{name='小强', password='1qaz2wsx'}
                    Log.e("服务端返回", loginWithUser.toString());  // User{name='小强', password='1qaz2wsx'}
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.btn_login_type_inout) {
            User user = new User();
            user.setName("小王");
            user.setPassword("123321");
            Log.e("客户端原对象", user.toString());  // User{name='小王', password='123321'}
            try {
                if (loginApi != null) {
                    User loginWithUser = loginApi.loginTypeInout(user);
                    Log.e("客户端对象", user.toString());  // User{name='小强', password='1qaz2wsx'}
                    Log.e("服务端返回", loginWithUser.toString());  // User{name='小强', password='1qaz2wsx'}
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}