//Assumes proper Java JNI class has been implemented
// Correct usage of JNI for object manipulation
#include <jni.h>
#include <iostream>

extern "C" {
    JNIEXPORT void JNICALL Java_MyClass_nativeMethod(JNIEnv *env, jobject obj) {
        // Safely accessing Java object methods without direct pointers
        jclass clazz = env->GetObjectClass(obj);  // Get the class of the object

        jmethodID methodID = env->GetMethodID(clazz, "someMethod", "()V");
        if (methodID != nullptr) {
            env->CallVoidMethod(obj, methodID);  // Correct way to call a Java method from JNI
        } else {
            std::cerr << "Method not found!" << std::endl;
        }
    }
}

