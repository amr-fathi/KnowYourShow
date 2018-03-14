package com.manish.knowyourshow.app.service

import com.manish.knowyourshow.app.model.SearchResponse
import reactor.core.publisher.Mono

internal interface ShowInfoService {
    fun fromShowName(showMono: Mono<String>): Mono<SearchResponse>
}