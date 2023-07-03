package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.order.Order;
import ru.praktikum_services.qa_scooter.order.OrderAssertions;
import ru.praktikum_services.qa_scooter.order.OrderClient;
import ru.praktikum_services.qa_scooter.order.OrderGenerator;

@RunWith(Parameterized.class)
public class AnyColorOrderSuccessfulTest{
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
    private final OrderGenerator generator = new OrderGenerator();

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
    private int track;

    public AnyColorOrderSuccessfulTest(String firstName, String lastName, String address, String metroStation, String phone, String rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] params(){
        return new Object[][] {
                {"Иванов", "Иван", "Липовая ал. 15", "25", "777 777 777", "42", "2023-07-05", "-", new String[]{"BLACK"}},
                {"Иванов", "Иван", "Липовая ал. 15", "25", "777 777 777", "42", "2023-07-05", "-", new String[]{"GREY"}},
//                {"Иванов", "Иван", "Липовая ал. 15", "25", "777 777 777", "42", "2023-07-05", "-", new String[]{"GREY", "BLACK"}},
//                {"Иванов", "Иван", "Липовая ал. 15", "25", "777 777 777", "42", "2023-07-05", "-", new String[]{}},
        };
    }

    @Test
    @DisplayName("Create order with any scooter color successful")
    @Description("Parameterized test to check that order is created successfully with any combination of scooter colors")
    public void scooterColorAnyNoneMultipleOrderSuccessful(){
        Order order = generator.generate(this);
        ValidatableResponse response = client.createOrder(order);
        track = check.createdSuccessfully(response);
        assert track > 0;
    }

    @After
    public void deleteOrder(){
        if (track > 0) {
            client.deleteOrder(track);
        }
    }

}
