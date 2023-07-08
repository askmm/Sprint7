package ru.praktikum_services.qa_scooter.order;

import com.google.gson.Gson;
import io.qameta.allure.Step;

public class OrderGenerator {
    @Step("Create order obj from parameterized class data")
    public Order generate(Object testData) {
        Gson gson = new Gson();
        String json = gson.toJson(testData);
        return gson.fromJson(json, Order.class);
    }
}
