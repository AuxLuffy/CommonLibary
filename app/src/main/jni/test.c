//
// Created by lenovo on 2017/9/21.
//
#include "com_lenovo_service_basicpubliclibrary_jniDemo_myJNI.h"
#include <stdio.h>

JNIEXPORT jstring JNICALL Java_com_lenovo_service_basicpubliclibrary_jniDemo_myJNI_sayHello
        (JNIEnv *env, jobject thiz){
    printf("c 中的sayhello方法");
    jclass clazz = (*env)->FindClass(env,"com/lenovo/service/basicpubliclibrary/jniDemo/JNITestActivity");
    if(clazz == 0){
        printf("jni not found JNITestActivity");
    }
    jmethodID id = (*env)->GetStaticMethodID(env,clazz,"calledbyjni","(Ljava/lang/String;)V");
    if(id == 0){
        printf("jni not found calledbyjni");
    }
    jstring msg = (*env)->NewStringUTF(env,"msg send by callJavaMethod in test.c");
    (*env)->CallStaticVoidMethod(env,clazz, id, msg);
    return (*env)->NewStringUTF(env,"called sayHello method success!");
}

JNIEXPORT void JNICALL Java_com_lenovo_service_basicpubliclibrary_jniDemo_myJNI_set
(JNIEnv *env, jobject thiz, jstring string){
    printf("c 中的set方法");
    char* str = (char*)(*env)->GetStringUTFChars(env,string,NULL);
    printf("%s\n",str);
    (*env)->ReleaseStringUTFChars(env,string,str);
}

//void callJavaMethod(JNIEnv *env,jobject thiz){
//
//
//}
