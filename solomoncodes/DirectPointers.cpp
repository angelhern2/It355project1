#include <jni.h>
#include <stdio.h>
//Assumes proper Java JNI class has been implemented
// Correct usage of JNI for object manipulation
extern "C" JNIEXPORT void JNICALL Java_MyClass_nativeMethod(JNIEnv *env, jobject obj) {
    jclass clazz = (*env)->GetObjectClass(env, obj);  // Access class safely through JNI functions

    jmethodID mid = (*env)->GetMethodID(env, clazz, "someMethod", "()V");
    if (mid != NULL) {
        (*env)->CallVoidMethod(env, obj, mid);  // Safely call the method
    }
}
