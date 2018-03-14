package com.manish.knowyourshow

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
class KnowyourshowApplication

fun main(args: Array<String>) {
    runApplication<KnowyourshowApplication>(*args)
}
