package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.courier.Credentials;

public class CourierLoginTest extends CourierTest{

    @Test
    @DisplayName("Courier logged in successfully")
    @Description("Check if courier login successfully with correct auth data")
    public void loggedInSuccessfully() {
        courier = generator.randomCourier();
        client.createCourier(courier);

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assert courierId > 0;
    }

    @Test
    @DisplayName("Courier log in with wrong password fails")
    @Description("Check if courier login with wrong password fails")
    public void loginWithWrongPasswordFail() {
        getCourierId();
        ValidatableResponse response = wrongCredentialsLogin(courier.getLogin(), generator.randomPassword());
        check.userNotFound(response);
    }


    @Test
    @DisplayName("Courier log in with wrong login fails")
    @Description("Check if courier login with wrong login fails")
    public void loginWithWrongLoginFail() {
        getCourierId();
        ValidatableResponse response = wrongCredentialsLogin(generator.randomLogin(), courier.getPassword());
        check.userNotFound(response);
    }

    @Test
    @DisplayName("Courier log in without login fails")
    @Description("Check if courier login without login fails")
    public void loginWithEmptyLoginFail() {
        getCourierId();
        ValidatableResponse response = wrongCredentialsLogin("", courier.getPassword());
        check.checkBadRequest(response);

    }

    @Test
    @DisplayName("Courier log in without password fails")
    @Description("Check if courier login without password fails")
    public void loginWithEmptyPasswordFail() {
        getCourierId();
        ValidatableResponse response = wrongCredentialsLogin(courier.getLogin(), "");
        check.checkBadRequest(response);
    }


    @Step("Get courier id")
    private void getCourierId(){
        courier = generator.randomCourier();
        client.createCourier(courier);
        courierId = courierId();
    }

    @Step("Change courier credentials")
    private ValidatableResponse wrongCredentialsLogin(String login, String password){
        courier.setPassword(password);
        courier.setLogin(login);
        Credentials creds = Credentials.from(courier);
        ValidatableResponse response = client.logIn(creds);
        return response;
    }

}
