package org.sundqvist.svt.codetest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class SwapiApplication {
    @Bean
    fun restTemplate() = RestTemplate(SimpleClientHttpRequestFactory().apply { setConnectTimeout(5000); setReadTimeout(5000) })
}

fun main(args: Array<String>) {
    runApplication<SwapiApplication>(*args)
}
