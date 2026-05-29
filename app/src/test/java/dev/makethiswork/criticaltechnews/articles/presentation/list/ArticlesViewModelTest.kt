package dev.makethiswork.criticaltechnews.articles.presentation.list

import app.cash.turbine.test
import dev.makethiswork.criticaltechnews.R
import dev.makethiswork.criticaltechnews.articles.data.FakeArticleRepository
import dev.makethiswork.criticaltechnews.articles.domain.Article
import dev.makethiswork.criticaltechnews.core.domain.util.DataError
import dev.makethiswork.criticaltechnews.core.domain.util.Result
import dev.makethiswork.criticaltechnews.core.presentation.util.UiText
import dev.makethiswork.criticaltechnews.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class ArticlesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val zone = ZoneId.of("UTC")
    private val clock = Clock.fixed(Instant.parse("2026-05-29T12:00:00Z"), zone)
    private val repository = FakeArticleRepository()

    private fun viewModel() = ArticlesViewModel(repository, clock)

    @Test
    fun `articles are grouped into day sections in descending order`() = runTest {
        repository.result = Result.Success(
            listOf(
                article("a", Instant.parse("2026-05-29T10:00:00Z")),
                article("b", Instant.parse("2026-05-29T08:00:00Z")),
                article("c", Instant.parse("2026-05-28T20:00:00Z")),
            )
        )

        viewModel().state.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertNull(state.errorText)
            assertEquals(2, state.sections.size)
            assertEquals("Today", state.sections[0].dayLabel)
            assertEquals(2, state.sections[0].articles.size)
            assertEquals("Yesterday", state.sections[1].dayLabel)
            assertEquals(1, state.sections[1].articles.size)
        }
    }

    @Test
    fun `repository error surfaces as errorText and no sections`() = runTest {
        repository.result = Result.Error(DataError.Network.NO_INTERNET)

        viewModel().state.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertTrue(state.sections.isEmpty())
            assertEquals(UiText.StringResource(R.string.error_no_internet), state.errorText)
        }
    }

    @Test
    fun `clicking an article emits OpenArticle event`() = runTest {
        repository.result = Result.Success(emptyList())
        val vm = viewModel()

        vm.events.test {
            vm.onAction(ArticlesAction.OnArticleClick("https://example.com/1"))
            assertEquals(ArticlesEvent.OpenArticle("https://example.com/1"), awaitItem())
        }
    }

    @Test
    fun `retry after an error clears errorText and loads sections`() = runTest {
        repository.result = Result.Error(DataError.Network.SERVER_ERROR)
        val vm = viewModel()

        repository.result = Result.Success(
            listOf(article("a", Instant.parse("2026-05-29T10:00:00Z")))
        )
        vm.onAction(ArticlesAction.OnRetryClick)

        vm.state.test {
            val state = awaitItem()
            assertNull(state.errorText)
            assertEquals(1, state.sections.size)
        }
    }

    private fun article(id: String, publishedAt: Instant) = Article(
        id = id,
        title = "Title $id",
        description = null,
        imageUrl = null,
        url = "https://example.com/$id",
        sourceName = "BBC News",
        publishedAt = publishedAt,
    )
}
