package dev.makethiswork.criticaltechnews.articles.presentation.list

import dev.makethiswork.criticaltechnews.articles.presentation.list.model.ArticleDaySection
import dev.makethiswork.criticaltechnews.core.presentation.util.UiText

data class ArticlesState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val sections: List<ArticleDaySection> = emptyList(),
    val errorText: UiText? = null,
) {
    val isEmpty: Boolean
        get() = !isLoading && errorText == null && sections.isEmpty()
}
