package com.liuy.tinker;

/**
 * description:
 * author: freed on 2020/4/6
 * email: 674919909@qq.com
 * version: 1.0
 */
public class NativeJni {
    static {
        System.loadLibrary("native-lib");
    }
    public static String LIB_NAME = "nativeJni";
    public native void toStringforJni();

}
