package org.sundqvist.svt.codetest.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import org.springframework.web.client.RestTemplate
import org.sundqvist.svt.codetest.domain.Starship


class SwapiClientTest : FunSpec({

    val customerServiceServer = WireMockServer(9000)
    listener(WireMockListener(customerServiceServer, ListenerMode.PER_SPEC))

    test("getSpaceShips") {
        customerServiceServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/"))
                .willReturn(
                    aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(
                            """{
  "next": "http://localhost:9000/2",
  "results": [
    {
      "name": "CR90 corvette",
      "cost_in_credits": "3500000"
    }]}"""
                        )
                )
        )

        customerServiceServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/2"))
                .willReturn(
                    aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(
                            """{
  "results": [
    {
      "name": "corv2",
      "cost_in_credits": "10"
    }]}"""
                        )
                )
        )

        SwapiClient(RestTemplate(), "http://localhost:9000/").getSpaceShips() shouldBe listOf(
            Starship(
                "CR90 corvette",
                "3500000"
            ), Starship("corv2", "10")
        )

    }
})
