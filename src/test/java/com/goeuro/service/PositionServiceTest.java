package com.goeuro.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.goeuro.model.Position;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PositionServiceTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private PositionService positionService;

    @Before
    public void setUp(){
        positionService = new PositionService();
    }

    @Test
    public void shouldRetrieveValidLocation() throws Exception {

        stubFor(get(urlEqualTo("/my/resource/berlin"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBodyFile("berlin.json")));

        Position[] positions = positionService.query("http://localhost:8089/my/resource/", "berlin");

        assertThat(positions.length, is(6));
    }

    @Test
    public void shouldGetEmptyPositionsForUnknownLocation() throws Exception {

        stubFor(get(urlEqualTo("/my/resource/Unknown"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("[]")));

        Position[] positions = positionService.query("http://localhost:8089/my/resource/", "Unknown");
        assertThat(positions.length, is(0));


    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionInternalServerError() throws Exception {

        //exception.expect(RuntimeException.class);
        //exception.expectMessage("Failed : HTTP error code : 500");

        stubFor(get(urlEqualTo("/my/resource/madrid"))
                .willReturn(aResponse()
                        .withStatus(500) //internal server error
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBodyFile("berlin.json")));

        positionService.query("http://localhost:8089/my/resource/", "madrid");

    }


}