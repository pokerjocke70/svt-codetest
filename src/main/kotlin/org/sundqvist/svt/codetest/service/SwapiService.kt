package org.sundqvist.svt.codetest.service

import org.springframework.stereotype.Service
import org.sundqvist.svt.codetest.client.SwapiClient
import org.sundqvist.svt.codetest.domain.Starship

@Service
class SwapiService(private val swapiClient: SwapiClient) {

    fun getStarships(): List<Starship> {
        return swapiClient.getSpaceShips()
            .sortedBy { it.cost_in_credits.toLongOrNull() }
            .reversed()
            .take(15)
    }
}
