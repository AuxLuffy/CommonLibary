//
// Created by lenovo on 2017/9/14.
//

#include <jni.h>
#include "JniDemoActivity.h"
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL
Java_org_eshock_jnitest_JniDemoActivity_sum( JNIEnv* env,
                                                  jobject thiz,
                                                  jint x,
                                                  jint y )
{
    return x + y;
}


#ifdef __cplusplus
}
#endif