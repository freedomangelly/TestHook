package com.liuy.testhook.proxyhook;

import android.util.Log;

/**
 * description:
 * author: freed on 2020/3/28
 * email: 674919909@qq.com
 * version: 1.0
 */
public class ShoppingImpl implements Shopping {
    @Override
    public Object[] doShopping(long money) {
        Log.i("info","逛淘宝 ,逛商场,买买买!!");
        Log.i("info",String.format("花了%s块钱", money));
        return new Object[0];
    }
}
