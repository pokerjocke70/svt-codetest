package org.sundqvist.svt.codetest.service

import org.springframework.stereotype.Service
import org.sundqvist.svt.codetest.client.SwapiClient

@Service
class SwapiService(private val swapiClient: SwapiClient) {

    fun getStarships() = swapiClient.getSpaceShips()
        .sortedByDescending { it.cost_in_credits.toLongOrNull() }
        .take(10)
}
