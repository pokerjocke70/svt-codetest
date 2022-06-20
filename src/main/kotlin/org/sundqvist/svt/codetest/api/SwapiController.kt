package org.sundqvist.svt.codetest.api

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.sundqvist.svt.codetest.service.SwapiService

@RestController
class SwapiController(private val swapiService: SwapiService) {


    @GetMapping("/starships", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getStarships() = swapiService.getStarships()
        .map { Starship(it.name, it.cost_in_credits.toLongOrNull()) }

}

data class Starship(val name: String, val cost: Long?)
