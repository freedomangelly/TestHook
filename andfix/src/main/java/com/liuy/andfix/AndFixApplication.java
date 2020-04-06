package com.liuy.andfix;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * description:
 * author: freed on 2020/4/6
 * email: 674919909@qq.com
 * version: 1.0
 */
public class AndFixApplication extends Application {
    private static final String TAG = "euler";

    private static final String APATCH_PATH = "/out.apatch";
    /**
     * patch manager
     */
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            init();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() throws PackageManager.NameNotFoundException {
        // 初始化patch管理类
        mPatchManager=new PatchManager(this);
        // 初始化patch版本
        mPatchManager.init(this.getPackageManager().getPackageInfo(getPackageName(),0).versionName);
        // 加载已经添加到PatchManager中的patch
        mPatchManager.loadPatch();
    }

    private void update() {
        String patchFileStr = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
        try {
            mPatchManager.addPatch(patchFileStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
