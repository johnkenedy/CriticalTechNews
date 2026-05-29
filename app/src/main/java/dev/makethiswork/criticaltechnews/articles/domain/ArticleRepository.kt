package dev.makethiswork.criticaltechnews.articles.domain

import dev.makethiswork.criticaltechnews.core.domain.util.DataError
import dev.makethiswork.criticaltechnews.core.domain.util.Result

interface ArticleRepository {
    suspend fun getHeadlines(): Result<List<Article>, DataError.Network>
}
