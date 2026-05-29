package dev.makethiswork.criticaltechnews.articles.data

import com.squareup.moshi.Moshi
import dev.makethiswork.criticaltechnews.core.domain.util.DataError
import dev.makethiswork.criticaltechnews.core.domain.util.Result
import dev.makethiswork.criticaltechnews.util.readResource
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsArticleRepositoryTest {

    private lateinit var server: MockWebServer
    private lateinit var repository: NewsArticleRepository

    @Before
    fun setup() {
        server = MockWebServer()
        server.start()
        val service = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
            .create(NewsApiService::class.java)
        repository = NewsArticleRepository(service)
    }

    @After
    fun teardown() {
        server.shutdown()
    }

    @Test
    fun `successful response is mapped to domain articles`() = runTest {
        server.enqueue(MockResponse().setResponseCode(200).setBody(readResource("/articles/top_headlines_valid.json")))

        val result = repository.getHeadlines()

        assertTrue(result is Result.Success)
        val articles = (result as Result.Success).data
        assertEquals(1, articles.size)
        assertEquals("Hello world", articles[0].title)
        assertEquals("BBC News", articles[0].sourceName)
        assertEquals("https://example.com/1", articles[0].url)
    }

    @Test
    fun `401 is mapped to UNAUTHORIZED`() = runTest {
        server.enqueue(MockResponse().setResponseCode(401).setBody("{}"))

        val result = repository.getHeadlines()

        assertTrue(result is Result.Error)
        assertEquals(DataError.Network.UNAUTHORIZED, (result as Result.Error).error)
    }

    @Test
    fun `400 is mapped to BAD_REQUEST`() = runTest {
        server.enqueue(MockResponse().setResponseCode(400).setBody("{}"))

        val result = repository.getHeadlines()

        assertTrue(result is Result.Error)
        assertEquals(DataError.Network.BAD_REQUEST, (result as Result.Error).error)
    }

    @Test
    fun `malformed body is mapped to SERIALIZATION`() = runTest {
        server.enqueue(MockResponse().setResponseCode(200).setBody("this is not json"))

        val result = repository.getHeadlines()

        assertTrue(result is Result.Error)
        assertEquals(DataError.Network.SERIALIZATION, (result as Result.Error).error)
    }

    @Test
    fun `removed and url-less rows are dropped`() = runTest {
        server.enqueue(MockResponse().setResponseCode(200).setBody(readResource("/articles/top_headlines_with_junk.json")))

        val result = repository.getHeadlines()

        assertTrue(result is Result.Success)
        val articles = (result as Result.Success).data
        assertEquals(1, articles.size)
        assertEquals("Valid", articles[0].title)
    }

    @Test
    fun `real captured response maps cleanly`() = runTest {
        server.enqueue(MockResponse().setResponseCode(200).setBody(readResource("/articles/top_headlines_real_sample.json")))

        val result = repository.getHeadlines()

        assertTrue(result is Result.Success)
        val articles = (result as Result.Success).data
        assertEquals(2, articles.size)
        assertEquals("CBS News", articles[0].sourceName)
        assertTrue(articles[0].title.startsWith("Live Updates: Vance says U.S. and Iran close but \"not there yet\""))
        assertEquals("Associated Press", articles[1].sourceName)
    }
}
