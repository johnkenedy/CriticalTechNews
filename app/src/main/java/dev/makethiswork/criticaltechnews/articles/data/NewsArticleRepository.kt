package dev.makethiswork.criticaltechnews.articles.data

import dev.makethiswork.criticaltechnews.BuildConfig
import dev.makethiswork.criticaltechnews.articles.data.mappers.toArticleOrNull
import dev.makethiswork.criticaltechnews.articles.domain.Article
import dev.makethiswork.criticaltechnews.articles.domain.ArticleRepository
import dev.makethiswork.criticaltechnews.core.data.networking.safeCall
import dev.makethiswork.criticaltechnews.core.domain.util.DataError
import dev.makethiswork.criticaltechnews.core.domain.util.Result
import dev.makethiswork.criticaltechnews.core.domain.util.map

class NewsArticleRepository(
    private val newsApiService: NewsApiService,
) : ArticleRepository {
    override suspend fun getHeadlines(): Result<List<Article>, DataError.Network> {
        return safeCall {
            newsApiService.getTopHeadlines(sources = BuildConfig.SOURCE_ID)
        }.map { response ->
            response.articles.mapNotNull { it.toArticleOrNull() }
        }
    }
}
