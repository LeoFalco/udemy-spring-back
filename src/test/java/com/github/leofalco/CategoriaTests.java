package com.github.leofalco;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriaTests {

    private static final String BASE_URL = "http://localhost:8080/";

    private static HttpClient httpClient = HttpClientBuilder.create().build();

    @Test
    public void givenNotFound_whenTryToGetCategoria_withAnNotExistentId() throws IOException {

        HttpUriRequest request = new HttpGet(BASE_URL + "/categorias/0");
        HttpResponse httpResponse = httpClient.execute(request);
        assertEquals(HttpStatus.NOT_FOUND.value(), httpResponse.getStatusLine().getStatusCode());
    }
}
