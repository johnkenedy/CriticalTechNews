package dev.makethiswork.criticaltechnews.articles.presentation.list

import dev.makethiswork.criticaltechnews.R
import dev.makethiswork.criticaltechnews.core.domain.util.DataError
import dev.makethiswork.criticaltechnews.core.presentation.util.UiText

fun DataError.Network.toUiText(): UiText {
    val resId = when (this) {
        DataError.Network.NO_INTERNET -> R.string.error_no_internet
        DataError.Network.REQUEST_TIMEOUT -> R.string.error_request_timeout
        DataError.Network.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        DataError.Network.BAD_REQUEST -> R.string.error_bad_request
        DataError.Network.UNAUTHORIZED -> R.string.error_unauthorized
        DataError.Network.SERVER_ERROR -> R.string.error_server
        DataError.Network.SERIALIZATION -> R.string.error_serialization
        DataError.Network.UNKNOWN -> R.string.error_unknown
    }
    return UiText.StringResource(resId)
}
