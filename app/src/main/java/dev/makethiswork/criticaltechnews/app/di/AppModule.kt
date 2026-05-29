package dev.makethiswork.criticaltechnews.app.di

import com.squareup.moshi.Moshi
import dev.makethiswork.criticaltechnews.BuildConfig
import dev.makethiswork.criticaltechnews.articles.data.NewsApiService
import dev.makethiswork.criticaltechnews.articles.data.NewsArticleRepository
import dev.makethiswork.criticaltechnews.articles.domain.ArticleRepository
import dev.makethiswork.criticaltechnews.core.data.networking.HttpClientFactory
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        HttpClientFactory(
            apiKey = BuildConfig.NEWS_API_KEY,
            enableLogging = BuildConfig.DEBUG,
        ).build()
    }

    single { Moshi.Builder().build() }

    single<NewsApiService> {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(get<OkHttpClient>())
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .build()
            .create(NewsApiService::class.java)
    }

    singleOf(::NewsArticleRepository) bind ArticleRepository::class
}
