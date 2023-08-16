package com.example.api;

import org.testng.annotations.*;
import io.qameta.allure.*;
import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import com.example.connector.Connector;

@Epic("Reqress API")
@Feature("GET API")
@Story("GET API Testing")
public class TestGetRequest {
    private Connector connector = new Connector();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Get user details")
    public void testGetRequest() {
        Response response = connector.getRequest(1);

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getInt("data.id"), 1);
        assertEquals(response.jsonPath().getString("data.email"), "george.bluth@reqres.in");
        assertEquals(response.jsonPath().getString("data.first_name"), "George");
        assertEquals(response.jsonPath().getString("data.last_name"), "Bluth");
    }
}
