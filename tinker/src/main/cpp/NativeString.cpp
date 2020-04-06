//
// Created by freed on 2020/4/6.
//
#include "com_liuy_tinker_NativeJni.h"
#include "android_log.h"

extern "C" JNIEXPORT void JNICALL Java_com_liuy_tinker_NativeJni_toStringforJni
        (JNIEnv *env, jobject ooject) {
    LOGI("hello word");
}

