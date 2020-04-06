package com.liuy.testhook.proxyhook.staticproxy;

import com.liuy.testhook.proxyhook.Shopping;
import com.liuy.testhook.proxyhook.ShoppingImpl;

import java.util.Arrays;

/**
 * @author weishu
 * @date 16/1/28
 */
public class TestStatic {
    public static void main(String[] args) {

        // 原始的厂家
        Shopping women = new ShoppingImpl();

        System.out.println(Arrays.toString(women.doShopping(100)));

        // 换成代购
        women = new ProxyShopping(women);

        System.out.println(Arrays.toString(women.doShopping(100)));
    }
}
