package com.example.server.bean;

import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {

    private String name;
    private String password;

    public User() {

    }

    protected User(Parcel in) {
        name = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
    }

    /**
     * 手动添加
     * 解决Aidl 找不到符号 方法 readFromParcel(Parcel)问题
     * 方法名一定是readFromParcel，内部实现复制User(Parcel in)方法即可
     *
     * @param in
     */
    public void readFromParcel(Parcel in) {
        name = in.readString();
        password = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
