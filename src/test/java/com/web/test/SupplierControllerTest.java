package com.web.test;

import com.web.model.Supplier;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SupplierControllerTest {
    @Autowired
    Environment environment;

    @LocalServerPort
    int port;

    @Test
    public void getAllApi() throws Exception {
        String plainCreds = "Theo:Test";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes, false);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/supplier";
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getByNameApi() throws Exception {
        Supplier mockSupplier = new Supplier(1, "Panzani");

        String plainCreds = "Theo:Test";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes, false);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/supplier/name/Panzani";
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);

        Assertions.assertEquals(mockSupplier.getId(), new JSONArray(response.getBody().toString()).getJSONObject(0).get("id"));
    }
}
