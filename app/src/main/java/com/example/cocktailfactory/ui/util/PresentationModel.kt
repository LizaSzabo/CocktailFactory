package com.example.cocktailfactory.ui.util

import co.zsmb.rainbowcake.withIOContext
import com.example.cocktailfactory.data.network.util.NetworkError
import com.example.cocktailfactory.data.network.util.NetworkResponse
import com.example.cocktailfactory.data.network.util.NetworkResult
import com.example.cocktailfactory.data.network.util.UnknownHostError
import kotlinx.coroutines.CoroutineScope

sealed class PresentationResponse<out T : Any>

class PresentationResult<out T : Any>(val result: T) : PresentationResponse<T>()

sealed class PresentationNoResult : PresentationResponse<Nothing>()

class PresentationNetworkError(val message: String?, val code: Int? = null) : PresentationNoResult()

suspend inline fun <NM : Any, PM : Any> makeNetworkCall(
    crossinline interactor: suspend CoroutineScope.() -> NetworkResponse<NM>,
    crossinline converter: (suspend CoroutineScope.(NM) -> PM)
): PresentationResponse<PM> = withIOContext {
    when (val networkResponse = interactor()) {
        is NetworkResult -> PresentationResult(converter(networkResponse.result))
        is NetworkError -> PresentationNetworkError(
            networkResponse.errorMessage,
            networkResponse.code
        )
        is UnknownHostError -> PresentationNetworkError(null, null)
    }
}

suspend fun makeNetworkCall(
    interactor: suspend CoroutineScope.() -> NetworkResponse<Unit>
): PresentationResponse<Unit> = makeNetworkCall(interactor) { }
