package dev.makethiswork.criticaltechnews.articles.data

import dev.makethiswork.criticaltechnews.articles.domain.Article
import dev.makethiswork.criticaltechnews.articles.domain.ArticleRepository
import dev.makethiswork.criticaltechnews.core.domain.util.DataError
import dev.makethiswork.criticaltechnews.core.domain.util.Result

class FakeArticleRepository : ArticleRepository {
    var result: Result<List<Article>, DataError.Network> = Result.Success(emptyList())

    override suspend fun getHeadlines(): Result<List<Article>, DataError.Network> = result
}
