package com.example.connector;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import com.example.data.Endpoint;

public class Connector {
    private String baseUrl = Endpoint.BASE_URL;

    public Response getRequest(int userId) {
        String path = Endpoint.USERS + "/" + userId;
        
        return given()
            .when()
            .get(baseUrl + path)
            .then()
            .extract().response();
    }

    public Response postRequest(String payload) {
        String path = Endpoint.USERS;
        
        return given()
            .header("Content-Type", "application/json")
            .body(payload)
            .when()
            .post(baseUrl + path)
            .then()
            .extract().response();
    }
}
