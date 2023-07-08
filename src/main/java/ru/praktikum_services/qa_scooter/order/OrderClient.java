package ru.praktikum_services.qa_scooter.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum_services.qa_scooter.Client;

public class OrderClient extends Client {
    static final String ORDER_API = "/orders";

    @Step("Create order")
    public ValidatableResponse createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_API)
                .then().log().all();
    }

    @Step("Delete order")
    public void deleteOrder(int track) {
        spec()
                .put(ORDER_API + "/cancel/?track=" + track)
                .then().log().all();
    }

    @Step("Get order list")
    public ValidatableResponse getOrderList(){
        return spec()
                .get(ORDER_API)
                .then();
    }


}
