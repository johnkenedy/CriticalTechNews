package dev.makethiswork.criticaltechnews.articles.presentation.list.mappers

import dev.makethiswork.criticaltechnews.articles.domain.Article
import dev.makethiswork.criticaltechnews.articles.presentation.list.model.ArticleDaySection
import dev.makethiswork.criticaltechnews.articles.presentation.list.model.ArticleUi
import dev.makethiswork.criticaltechnews.core.presentation.util.DateFormatter
import java.time.Instant
import java.time.ZoneId

fun Article.toUi(now: Instant, zoneId: ZoneId): ArticleUi {
    return ArticleUi(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        url = url,
        sourceName = sourceName,
        timeAgo = DateFormatter.timeAgo(publishedAt, now, zoneId),
    )
}

fun List<Article>.toDaySections(now: Instant, zoneId: ZoneId): List<ArticleDaySection> {
    return sortedByDescending { it.publishedAt }
        .groupBy { DateFormatter.dayBucket(it.publishedAt, now, zoneId) }
        .map { (dayLabel, articles) ->
            ArticleDaySection(
                dayLabel = dayLabel,
                articles = articles.map { it.toUi(now, zoneId) },
            )
        }
}
