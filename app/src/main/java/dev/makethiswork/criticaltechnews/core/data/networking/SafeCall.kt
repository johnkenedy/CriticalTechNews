package dev.makethiswork.criticaltechnews.core.data.networking

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import dev.makethiswork.criticaltechnews.core.domain.util.DataError
import dev.makethiswork.criticaltechnews.core.domain.util.Result
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend inline fun <T> safeCall(execute: () -> T): Result<T, DataError.Network> {
    return try {
        Result.Success(execute())
    } catch (e: UnknownHostException) {
        Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: SocketTimeoutException) {
        Result.Error(DataError.Network.REQUEST_TIMEOUT)
    } catch (e: HttpException) {
        Result.Error(e.code().toNetworkError())
    } catch (e: JsonEncodingException) {
        Result.Error(DataError.Network.SERIALIZATION)
    } catch (e: JsonDataException) {
        Result.Error(DataError.Network.SERIALIZATION)
    } catch (e: IOException) {
        Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        Result.Error(DataError.Network.UNKNOWN)
    }
}

fun Int.toNetworkError(): DataError.Network {
    return when (this) {
        400 -> DataError.Network.BAD_REQUEST
        401 -> DataError.Network.UNAUTHORIZED
        408 -> DataError.Network.REQUEST_TIMEOUT
        429 -> DataError.Network.TOO_MANY_REQUESTS
        in 500..599 -> DataError.Network.SERVER_ERROR
        else -> DataError.Network.UNKNOWN
    }
}
