package com.manish.knowyourshow.app.config

import com.manish.knowyourshow.app.handler.ErrorHandler
import com.manish.knowyourshow.app.handler.ShowInfoHandler
import com.manish.knowyourshow.app.router.ApiRouter
import com.manish.knowyourshow.app.service.ShowInfoService
import com.manish.knowyourshow.app.service.ShowInfoServiceImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@EnableWebFlux
internal class ApplicationConfig {

    @Bean
    fun showInfoHandler(showInfoService: ShowInfoService, errorHandler: ErrorHandler) = ShowInfoHandler(showInfoService, errorHandler)

    @Bean
    internal fun errorHandler() = ErrorHandler()

    @Bean
    internal fun apiRouter(showInfoHandler: ShowInfoHandler, errorHandler: ErrorHandler) = ApiRouter(showInfoHandler, errorHandler).doRoute()

    @Bean
    internal fun showInfoService(@Value("\${ShowInfoServiceImpl.endPoint}") endPoint: String): ShowInfoService = ShowInfoServiceImpl(endPoint)

}