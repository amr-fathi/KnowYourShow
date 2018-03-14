package com.manish.knowyourshow.app.handler

import com.manish.knowyourshow.app.exception.PathNotFoundException
import com.manish.knowyourshow.app.extensions.getLogger
import com.manish.knowyourshow.app.extensions.toBody
import com.manish.knowyourshow.app.model.ErrorResponse
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.status
import reactor.core.publisher.Mono

internal class ErrorHandler {

    companion object {
        private val logger = getLogger<ErrorHandler>()
        private const val NOT_FOUND = "Data not found"
    }

    @Suppress("UNUSED_PARAMETER")
    fun notFound(request: ServerRequest) =
            Mono.just(PathNotFoundException(NOT_FOUND)).transform(this::getResponse)


    fun throwableError(throwable: Throwable): Mono<ServerResponse> {
        logger.error(NOT_FOUND, throwable)
        return Mono.just(throwable).transform(this::getResponse)
    }

    private fun <T : Throwable> getResponse(monoError: Mono<T>): Mono<ServerResponse> =

            monoError.transform(ThrowableTranslator.Translate::throwable).flatMap {
                status(it.httpStatus).contentType(MediaType.APPLICATION_JSON_UTF8).toBody(ErrorResponse(it.message))
            }
}