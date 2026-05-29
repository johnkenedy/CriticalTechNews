package dev.makethiswork.criticaltechnews.articles.presentation.list

sealed interface ArticlesAction {
    data object OnRefresh : ArticlesAction
    data object OnRetryClick : ArticlesAction
    data class OnArticleClick(val url: String) : ArticlesAction
}
