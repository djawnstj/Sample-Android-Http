package com.djawnstj.http.api

import com.djawnstj.http.common.utils.debug
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit by lazy { createRetrofit() }

private const val BASE_HOST: String = "192.168.114.246"
private const val BASE_PORT: Int = 8080

private fun createRetrofit(): Retrofit {

    val httpLoggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor {
            debug("HttpLogging", it)
        }.apply { level = HttpLoggingInterceptor.Level.BODY }

    val okHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(30, TimeUnit.MINUTES) // 연결 시간 설정. 기본 10 초. 설정 가능 범위 0 ~ Int.MAX_VALUE.
            writeTimeout(30, TimeUnit.MINUTES) // 쓰기 시간 설정. 기본 10 초. 설정 가능 범위 0 ~ Int.MAX_VALUE.
            readTimeout(30, TimeUnit.MINUTES) // 읽기 시간 설정. 기본 10 초. 설정 가능 범위 0 ~ Int.MAX_VALUE.
        }.build()

    return Retrofit.Builder()
        .baseUrl("http://$BASE_HOST:$BASE_PORT/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

