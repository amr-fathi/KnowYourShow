package com.manish.knowyourshow.app.router

import com.manish.knowyourshow.app.handler.ErrorHandler
import com.manish.knowyourshow.app.handler.ShowInfoHandler
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

internal class ApiRouter(private val handler: ShowInfoHandler, private val errorHandler: ErrorHandler) {

    private companion object {
        private const val API_PATH = "/api"
        const val SHOW_PATH = "/show"
        const val SEARCH_ARG = "/{title}"
        const val ANY_PATH = "/**"
        const val SHOW_WITH_SEARCH_PATH = "$SHOW_PATH$SEARCH_ARG"
    }

    fun doRoute() = router {
        (accept(MediaType.APPLICATION_JSON_UTF8) and API_PATH).nest {
            GET(SHOW_WITH_SEARCH_PATH)(handler::getShowData)
            path(ANY_PATH)(errorHandler::notFound)
        }
    }
}