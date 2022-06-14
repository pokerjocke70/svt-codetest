package org.sundqvist.svt.codetest.service

import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.sundqvist.svt.codetest.client.SwapiClient
import org.sundqvist.svt.codetest.domain.Starship

class SwapiServiceTest : FunSpec() {
    init {
        test("getStarships should return 0 starship") {

            val swapiClient = mockk<SwapiClient>()

            every { swapiClient.getSpaceShips() } returns listOf()

            val swapiService = SwapiService(swapiClient)
            val starships = swapiService.getStarships()
            assertEquals(0, starships.size)
        }

        test("getStarships should return the 15 most expensive starships") {

            val swapiClient = mockk<SwapiClient>()

            every { swapiClient.getSpaceShips() } returns (1..1000).map {
                Starship(
                    "Starship $it",
                    if (it % 10 == 0) "unknown" else "${it * 10}"
                )
            }.toMutableList().apply { shuffle() }

            val swapiService = SwapiService(swapiClient)
            val starships = swapiService.getStarships()
            assertEquals(15, starships.size)
            assertEquals(Starship("Starship 999", "9990"), starships.first())
        }
    }

    //
}




