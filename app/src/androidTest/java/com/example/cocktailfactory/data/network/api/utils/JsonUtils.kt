package com.example.cocktailfactory.data.network.api.utils

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.InputStreamReader

inline fun <reified R> String.getResponse(gson: Gson): R {
    val json = getStringResource(gson::class.java, this)
    val adapter = gson.getAdapter(R::class.java)

    return adapter.fromJson(json) ?: throw IllegalArgumentException("Could not deserialize $this")
}

fun getStringResource(clazz: Class<*>, name: String): String {
    return clazz.classLoader?.getResourceAsStream(name)?.let { stream ->
        InputStreamReader(stream).use { return@use it.readText() }
    }.orEmpty()
}

private const val HTTP_ERROR_CODE = 400

object FakeResponseType

fun String.getHttpException(gson: Gson): HttpException {
    val json = getStringResource(gson::class.java, this)
    return HttpException(
        Response.error<FakeResponseType>(
            HTTP_ERROR_CODE,
            json.toResponseBody("application/json".toMediaType())
        )
    )
}
