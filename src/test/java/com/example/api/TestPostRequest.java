package com.example.api;

import org.testng.annotations.*;
import io.qameta.allure.*;
import io.restassured.response.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import static org.testng.Assert.assertEquals;
import com.example.connector.Connector;

@Epic("Reqress API")
@Feature("POST API")
@Story("POST API Testing")
public class TestPostRequest {
    private Connector connector = new Connector();
    private JsonObject payload;
    
    private void loadPayloadFromFile() {
        try (FileReader reader = new FileReader("src/test/java/com/example/data/Payload.json")) {
            payload = JsonParser.parseReader(reader)
                .getAsJsonObject()
                .getAsJsonObject("payload")
                .getAsJsonObject("example")
                .getAsJsonObject("post")
                .getAsJsonObject("users");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void setup() {
        loadPayloadFromFile();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Post user data")
    public void testPostRequest() {
        Response response = connector.postRequest(payload.toString());

        assertEquals(response.getStatusCode(), 201);
        assertEquals(response.jsonPath().getString("name"), "John Doe");
        assertEquals(response.jsonPath().get("job"), "Software Engineer");
    }
}
