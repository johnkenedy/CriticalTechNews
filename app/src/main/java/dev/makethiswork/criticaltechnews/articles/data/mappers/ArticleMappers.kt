package dev.makethiswork.criticaltechnews.articles.data.mappers

import dev.makethiswork.criticaltechnews.BuildConfig
import dev.makethiswork.criticaltechnews.articles.data.dto.ArticleDto
import dev.makethiswork.criticaltechnews.articles.domain.Article
import java.time.Instant

fun ArticleDto.toArticleOrNull(): Article? {
    val url = url ?: return null
    val title = title?.takeIf { it.isNotBlank() && it != "[Removed]" } ?: return null
    val publishedAt = publishedAt?.let { runCatching { Instant.parse(it) }.getOrNull() } ?: return null

    return Article(
        id = url,
        title = title,
        description = description,
        imageUrl = urlToImage,
        url = url,
        sourceName = source?.name ?: BuildConfig.SOURCE_NAME,
        publishedAt = publishedAt,
    )
}
