package com.manish.knowyourshow.app.handler

import com.manish.knowyourshow.app.exception.InvalidParametersException
import org.springframework.http.HttpStatus
import reactor.core.publisher.Mono

internal class ThrowableTranslator private constructor(throwable: Throwable) {

    val httpStatus: HttpStatus
    val message: String

    init {
        this.httpStatus = getStatus(throwable)
        this.message = throwable.message.toString()
    }

    private fun getStatus(throwable: Throwable): HttpStatus =
            when (throwable) {
                is InvalidParametersException -> HttpStatus.BAD_REQUEST
                else -> HttpStatus.INTERNAL_SERVER_ERROR
            }

    companion object Translate {
        fun <T : Throwable> throwable(throwable: Mono<T>) = throwable.map(::ThrowableTranslator)
    }
}