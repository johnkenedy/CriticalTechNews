package dev.makethiswork.criticaltechnews.articles.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.makethiswork.criticaltechnews.articles.domain.ArticleRepository
import dev.makethiswork.criticaltechnews.articles.presentation.list.mappers.toDaySections
import dev.makethiswork.criticaltechnews.core.domain.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Clock

class ArticlesViewModel(
    private val repository: ArticleRepository,
    private val clock: Clock = Clock.systemDefaultZone(),
) : ViewModel() {

    private val _state = MutableStateFlow(ArticlesState())
    val state = _state.asStateFlow()

    private val _events = Channel<ArticlesEvent>()
    val events = _events.receiveAsFlow()

    init {
        loadHeadlines()
    }

    fun onAction(action: ArticlesAction) {
        when (action) {
            ArticlesAction.OnRefresh -> loadHeadlines(isRefresh = true)
            ArticlesAction.OnRetryClick -> loadHeadlines()
            is ArticlesAction.OnArticleClick -> viewModelScope.launch {
                _events.send(ArticlesEvent.OpenArticle(action.url))
            }
        }
    }

    private fun loadHeadlines(isRefresh: Boolean = false) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = !isRefresh,
                    isRefreshing = isRefresh,
                    errorText = null,
                )
            }

            when (val result = repository.getHeadlines()) {
                is Result.Success -> {
                    val sections = result.data.toDaySections(clock.instant(), clock.zone)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            sections = sections,
                            errorText = null,
                        )
                    }
                }

                is Result.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        errorText = result.error.toUiText(),
                    )
                }
            }
        }
    }
}
