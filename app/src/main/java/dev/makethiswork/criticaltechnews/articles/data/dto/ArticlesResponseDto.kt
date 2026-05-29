package dev.makethiswork.criticaltechnews.articles.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>,
)
