package ru.praktikum_services.qa_scooter.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.*;

public class OrderAssertions {
    @Step("Check status 201 created")
    public int createdSuccessfully(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track");
    }

    @Step("Check status 200 ok & non empty array returned")
    public void nonEmptyArray(ValidatableResponse response) {

        response.assertThat()
                .body("orders", notNullValue())
                .and()
                .body("orders", not(emptyArray()))
                .and()
                .statusCode(HTTP_OK)
                .log().all();
    }

}
