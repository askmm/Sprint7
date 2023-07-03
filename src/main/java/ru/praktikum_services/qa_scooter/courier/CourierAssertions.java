package ru.praktikum_services.qa_scooter.courier;

import io.restassured.response.ValidatableResponse;

import io.qameta.allure.Step;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierAssertions {
    @Step("Check status 201 ok")
    public void createdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    @Step("Check status 409 conflict")
    public void creationFailsDueConflict(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Check status 200 ok")
    public int loggedInSuccessfully(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    @Step("Check status 404 not found")
    public void userNotFound(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_NOT_FOUND);
    }

    @Step("Check status 400 bad request")
    public void checkBadRequest(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST);
    }

}
