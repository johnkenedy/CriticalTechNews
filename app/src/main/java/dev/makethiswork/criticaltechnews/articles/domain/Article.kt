package dev.makethiswork.criticaltechnews.articles.domain

import java.time.Instant

data class Article(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val url: String,
    val sourceName: String,
    val publishedAt: Instant,
)
