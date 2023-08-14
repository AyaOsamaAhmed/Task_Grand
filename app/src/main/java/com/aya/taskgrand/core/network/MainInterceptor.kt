package com.aya.taskgrand.core.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        requestBuilder.addHeader("X-Auth-Token", "5c6e31f39a2846d881a3c954f73625a1")

        return chain.proceed(requestBuilder.build())
    }
}