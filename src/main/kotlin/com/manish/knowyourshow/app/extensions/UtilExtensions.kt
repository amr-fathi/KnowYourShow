package com.manish.knowyourshow.app.extensions

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.toMono

fun <T : Any> ServerResponse.BodyBuilder.toBody(value: T) = this.body(value.toMono(), value.javaClass)
inline fun <reified T : Any> getLogger(): Logger = LoggerFactory.getLogger(T::class.java)
