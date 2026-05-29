package dev.makethiswork.criticaltechnews.core.data.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class HttpClientFactory(
    private val apiKey: String,
    private val enableLogging: Boolean,
) {
    fun build(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .apply {
                if (enableLogging) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }
            .build()
    }
}
