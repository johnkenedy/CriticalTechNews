package dev.makethiswork.criticaltechnews.articles.presentation.list.model

data class ArticleUi(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val url: String,
    val sourceName: String,
    val timeAgo: String,
)
