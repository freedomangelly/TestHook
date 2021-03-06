package com.liuy.testhook.hookProvider;

import android.app.Application;
import android.content.Context;

import java.io.File;

/**
 * 一定需要Application，并且在attachBaseContext里面Hook
 * 因为provider的初始化非常早，比Application的onCreate还要早
 * 在别的地方hook都晚了。
 *
 * @author weishu
 * @date 16/3/29
 */
public class HookApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        try {
//            SandHook.addHookClass(ActivityHooker.class);
//        } catch (HookErrorException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        try {
            File apkFile = getFileStreamPath("testcontentprovider-debug.apk");
            if (!apkFile.exists()) {
                Utils.extractAssets(base, "testcontentprovider-debug.apk");
            }

            File odexFile = getFileStreamPath("test.odex");

            // Hook ClassLoader, 让插件中的类能够被成功加载
            BaseDexClassLoaderHookHelper.patchClassLoader(getClassLoader(), apkFile, odexFile);
            ProviderHelper.installProviders(base, getFileStreamPath("testcontentprovider-debug.apk"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
