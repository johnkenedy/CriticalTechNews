package dev.makethiswork.criticaltechnews.core.domain.util

sealed interface DataError : Error {
    enum class Network : DataError {
        NO_INTERNET,
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        BAD_REQUEST,
        UNAUTHORIZED,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
    }
}
