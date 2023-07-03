package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.courier.*;

import java.util.Map;

public class CourierCreationTest extends CourierTest{

    @Test
    @DisplayName("Create courier successfully")
    @Description("Create courier with random data successfully")
    public void createCourierSuccessfully() {
        courier = generator.randomCourier();
        ValidatableResponse courierResponse = client.createCourier(courier);
        check.createdSuccessfully(courierResponse);

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
    }

    @Test
    @DisplayName("Create courier duplicate fail")
    public void createCourierDuplicateFail() {
        courier = generator.randomCourier();
        client.createCourier(courier);
        courierId = courierId();
        ValidatableResponse courierResponse = client.createCourier(courier);
        check.creationFailsDueConflict(courierResponse);
    }

    @Test
    @DisplayName("Create courier with same login fail")
    public void createCourierSameLoginFail() {
        courier = generator.randomCourier();
        client.createCourier(courier);
        courierId = courierId();
        courier = generator.randomCourier(courier.getLogin());
        ValidatableResponse courierResponse = client.createCourier(courier);
        check.creationFailsDueConflict(courierResponse);
    }

    @Test
    @DisplayName("Create courier with empty login fail")
    public void createCourierEmptyLoginFail() {
        courier = generator.randomCourier();
        courier.setLogin("");
        ValidatableResponse courierResponse = client.createCourier(courier);
        check.checkBadRequest(courierResponse);
    }

    @Test
    @DisplayName("Create courier with empty password fail")
    public void createCourierEmptyPasswordFail() {
        courier = generator.randomCourier();
        courier.setPassword("");
        ValidatableResponse courierResponse = client.createCourier(courier);
        check.checkBadRequest(courierResponse);
    }

    @Test
    @DisplayName("Create courier without password field fail")
    public void createCourierWithoutPasswordFail() {
        courier = generator.randomCourier();
        ValidatableResponse courierResponse = client.createCourier(
                Map.of("login", courier.getLogin()));
        check.checkBadRequest(courierResponse);
    }

    @Test
    @DisplayName("Create courier without login field fail")
    public void createCourierWithoutLoginFail() {
        courier = generator.randomCourier();
        ValidatableResponse courierResponse = client.createCourier(
                Map.of("password", courier.getPassword(), "firstName", courier.getFirstName()));
        check.checkBadRequest(courierResponse);
    }


}
