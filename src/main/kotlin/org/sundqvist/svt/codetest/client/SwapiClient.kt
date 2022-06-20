package org.sundqvist.svt.codetest.client

import kotlinx.collections.immutable.toImmutableList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.sundqvist.svt.codetest.domain.Starship

@Component
class SwapiClient(
    private val restTemplate: RestTemplate,
    @Value("\${swapi.uri}") private val swapiUri: String
) {

    private val logger = KotlinLogging.logger {}

    @Retryable(maxAttempts = 2, backoff = Backoff(value = 50L, multiplier = 2.0))
    fun getSpaceShips(): List<Starship> =
        generateSequence({ loadStarships(swapiUri) }, { if (it.next != null) loadStarships(it.next) else null })
            .flatMap { it.results }
            .apply {
                logger.info { "Returning ${count()} starships" }
            }.toImmutableList()


    private fun loadStarships(page: String) = restTemplate.getForObject(page, StarshipResponse::class.java)!!
}

private data class StarshipResponse(val next: String?, val results: List<Starship>)
