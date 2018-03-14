package com.manish.knowyourshow.app.service

import com.manish.knowyourshow.app.exception.GetShowInfoException
import com.manish.knowyourshow.app.exception.InvalidParametersException
import com.manish.knowyourshow.app.model.SearchResponse
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


internal open class ShowInfoServiceImpl(private val endPoint: String, private val webClient: WebClient = WebClient.create()) : ShowInfoService {

    private companion object {
        const val MISSING_SHOW_NAME = "missing show name"
        const val ERROR_GETTING_SHOW_INFO = "error getting show info"
    }

    override fun fromShowName(showMono: Mono<String>) = showMono
            .transform(this::buildUrl)
            .transform(this::get)
            .onErrorResume { GetShowInfoException(ERROR_GETTING_SHOW_INFO, it).toMono() }

    internal open fun buildUrl(showMono: Mono<String>) =
            showMono.flatMap {
                if (!it.isBlank()) ("$endPoint&t=$it").toMono()
                else InvalidParametersException(MISSING_SHOW_NAME).toMono()
            }

    internal open fun get(urlMono: Mono<String>) =
            urlMono.flatMap {
                webClient.get()
                        .uri(it)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve().bodyToMono<SearchResponse>().log()
            }

}