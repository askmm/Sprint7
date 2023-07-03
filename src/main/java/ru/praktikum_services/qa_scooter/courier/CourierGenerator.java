package ru.praktikum_services.qa_scooter.courier;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier generic() {
        return new Courier("Jack", "P@ssw0rd123", "Sparrow");
    }
    @Step("Generate random courier")
    public Courier randomCourier() {
        return new Courier(
                randomLogin(),
                randomPassword(),
                randomFirstName());
    }

    @Step("Generate random courier with fixed login")
    public Courier randomCourier(String login) {
        Courier courier = randomCourier();
        courier.setLogin(login);
        return courier;
    }

    public String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    public String randomLogin() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

    public String randomFirstName() {
        return RandomStringUtils.randomAlphanumeric(5, 10);
    }

}
