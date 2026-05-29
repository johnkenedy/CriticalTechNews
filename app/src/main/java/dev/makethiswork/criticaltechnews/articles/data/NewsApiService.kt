package dev.makethiswork.criticaltechnews.articles.data

import dev.makethiswork.criticaltechnews.articles.data.dto.ArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String,
    ): ArticlesResponseDto
}
