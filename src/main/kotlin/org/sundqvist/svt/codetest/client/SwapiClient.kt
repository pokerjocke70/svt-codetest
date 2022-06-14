package org.sundqvist.svt.codetest.client

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.sundqvist.svt.codetest.domain.Starship

@Component
class SwapiClient(private val restTemplate: RestTemplate) {

    fun getSpaceShips(): List<Starship> {
        val starships = mutableListOf<Starship>()
        var exhausted = false
        var page = 1
        do {
            loadNext(page).let {
                if (it.next == null) {
                    exhausted = true
                } else {
                    page++
                }
                starships.addAll(it.results)
            }
        } while (!exhausted)

        return starships.toList()
    }


    private fun loadNext(page: Int): StarshipResponse {
        return restTemplate.getForObject("https://swapi.dev/api/starships/?page=$page", StarshipResponse::class.java)!!
    }
}

private data class StarshipResponse(val next: String?, val results: List<Starship>)