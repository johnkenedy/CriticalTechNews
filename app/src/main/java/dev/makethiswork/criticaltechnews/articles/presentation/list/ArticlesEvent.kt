package dev.makethiswork.criticaltechnews.articles.presentation.list

sealed interface ArticlesEvent {
    data class OpenArticle(val url: String) : ArticlesEvent
}
