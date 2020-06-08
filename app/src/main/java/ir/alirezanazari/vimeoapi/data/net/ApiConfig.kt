package ir.alirezanazari.vimeoapi.data.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import ir.alirezanazari.vimeoapi.BuildConfig
import ir.alirezanazari.vimeoapi.internal.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiConfig {

    companion object {

        operator fun invoke(
            tokenInterceptor: TokenInterceptor
        ): Api {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor)
                .connectTimeout(16, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                okHttpClient.addInterceptor(httpLoggingInterceptor)
            }

            return Retrofit.Builder()
                .client(okHttpClient.build())
                .baseUrl(Constants.Net.BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)

        }

    }

}