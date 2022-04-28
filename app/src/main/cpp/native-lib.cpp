#include <jni.h>
#include <string>

extern "C" jstring
Java_com_myappventure_app_MyAppVentureApp_baseUrl(
        JNIEnv *env,
        jobject
) {
    std::string baseUrl = "https://myappventure-api.herokuapp.com/api/"; /* dev */
//    std::string baseUrl = "https://api.themoviedb.org/3/"; /* staging */
//    std::string baseUrl = "https://api.themoviedb.org/3/"; /* release */
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" jstring
Java_com_myappventure_app_MyAppVentureApp_apiKey(
        JNIEnv *env,
        jobject
) {
    std::string apiKey = "123"; /* dev */
//    std::string apiKey = "456"; /* staging */
//    std::string apiKey = "123456"; /* release */
    return env->NewStringUTF(apiKey.c_str());
}
