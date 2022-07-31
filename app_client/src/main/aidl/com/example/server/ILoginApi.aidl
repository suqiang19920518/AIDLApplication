package com.example.server;
import com.example.server.bean.User;
import com.example.server.callback.LoginCallback;
interface ILoginApi {

    void login(String name, String password);

    /**
     * oneway:用于修改远程调用的行为，被oneway修饰了的方法不可以有返回值，也不可以有带out或inout的参数
     * 本地调用(同步调用)
     * 远程调用(异步调用，即客户端不会被阻塞)
     */
    oneway void loginWithCallback(String name, String password, LoginCallback callback);

    oneway void modifyNames(in String[] nams);

    /**
     * in:表示数据只能由客户端流向服务端
     * （表现为服务端修改此参数，不会影响客户端的对象）
     */
    User loginTypeIn(in User user);

    /**
     * out:表示数据只能由服务端流向客户端
     * （表现为服务端收到的参数是空对象，并且服务端修改对象后客户端会同步变动）
     */
    User loginTypeOut(out User user);

    /**
     * inout:表示数据可在服务端与客户端之间双向流通
     * （表现为服务端能接收到客户端传来的完整对象，并且服务端修改对象后客户端会同步变动）
     */
    User loginTypeInout(inout User user);
}