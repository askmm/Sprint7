package ru.praktikum_services.qa_scooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum_services.qa_scooter.Client;

public class CourierClient extends Client {
    static final String COURIER_API = "/courier";

    @Step("Create new courier")
    public ValidatableResponse createCourier(Object courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_API)   // CREATE
                .then().log().all();
    }

    @Step("Courier log in")
    public ValidatableResponse logIn(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(COURIER_API + "/login")  // LOG IN
                .then().log().all();
    }

    @Step("Delete courier")
    public ValidatableResponse deleteCourier(int id) {
        return spec()
                .when()
                .delete(COURIER_API + "/" + id)  // LOG IN
                .then().log().all();
    }
}
