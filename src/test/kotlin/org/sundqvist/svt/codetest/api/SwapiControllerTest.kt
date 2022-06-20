package org.sundqvist.svt.codetest.api

import io.restassured.RestAssured.get
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.server.LocalServerPort
import org.sundqvist.svt.codetest.domain.Starship
import org.sundqvist.svt.codetest.service.SwapiService


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwapiControllerTest {

    @MockBean
    private lateinit var service: SwapiService

    @LocalServerPort
    private lateinit var port: String

    @Test
    fun testStarships() {

        Mockito.`when`(service.getStarships()).thenReturn(listOf(Starship("1", "20"), Starship("2", "30")))
        get("http://localhost:${port}/swapi/starships")
            .then()
            .assertThat()
            .statusCode(200)
            .body("size()", `is`(2))

    }
}
