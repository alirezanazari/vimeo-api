package ir.alirezanazari.vimeoapi.data.net

import ir.alirezanazari.vimeoapi.internal.Constants.Net.ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.Response


class TokenInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization" , ACCESS_TOKEN)
            .build()

        return chain.proceed(request)
    }
}