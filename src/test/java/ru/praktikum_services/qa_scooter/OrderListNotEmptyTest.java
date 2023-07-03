package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.order.OrderAssertions;
import ru.praktikum_services.qa_scooter.order.OrderClient;

public class OrderListNotEmptyTest {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName("Get order list with default parameters")
    @Description("Check that non-empty order list returns with default parameters")
    public void ordersWithoutParamReturnsNonEmptyList(){
        ValidatableResponse response = client.getOrderList();
        check.nonEmptyArray(response);
    }

}
