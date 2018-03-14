package com.manish.knowyourshow.app.handler

import com.manish.knowyourshow.app.model.SearchResponse
import com.manish.knowyourshow.app.service.ShowInfoService
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


internal class ShowInfoHandler(private val showInfoService: ShowInfoService, private val errorHandler: ErrorHandler) {

    private companion object {
        private const val SHOW_TITLE = "title"
    }

    internal fun getShowData(request: ServerRequest) = request.pathVariable(SHOW_TITLE)
            .toMono()
            .transform(this::buildResponse)
            .transform(this::serverResponse)
            .onErrorResume(errorHandler::throwableError)

    private fun buildResponse(show: Mono<String>) =
            show.transform(showInfoService::fromShowName).log()

    //TODO response is coming but when transforming to body from mono returns no body, don't know why
    private fun serverResponse(searchResponseMono: Mono<SearchResponse>): Mono<ServerResponse> =
            searchResponseMono.flatMap { ok()
                    .contentType(APPLICATION_JSON)
                    .body(fromObject(it))
            }.switchIfEmpty(notFound().build()).log()
}