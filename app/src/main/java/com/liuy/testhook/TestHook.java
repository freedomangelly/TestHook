package com.liuy.testhook;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;


/**
 * description:
 * author: freed on 2020/3/28
 * email: 674919909@qq.com
 * version: 1.0
 */
public class TestHook extends XC_MethodHook {
    private String  TAG="TestHook";



    @Override
    protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Log.i(TAG,"afterHookedMethod");
        XposedHelpers.findAndHookMethod(HookEntity.class,"onPause",this);
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Log.i(TAG,"beforeHookedMethod");
    }

    @Override
    public void callBeforeHookedMethod(MethodHookParam param) throws Throwable {
        super.callBeforeHookedMethod(param);
        Log.i(TAG,"callBeforeHookedMethod");
    }

    @Override
    public void callAfterHookedMethod(MethodHookParam param) throws Throwable {
        super.callAfterHookedMethod(param);
        Log.i(TAG,"callAfterHookedMethod");
    }
}
